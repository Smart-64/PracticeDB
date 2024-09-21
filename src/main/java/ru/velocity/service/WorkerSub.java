package ru.velocity.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.paho.client.mqttv3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.velocity.util.TransportVehicle;

import java.io.IOException;

public class WorkerSub extends Thread {
    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerSub.class);
    private MqttClient mqttClient;
    private final static String TOPIC_SUB = "topic_velocity";

    private final int id;
    private final int maxVelocity;

    private final WorkerPub workerPub;

    public WorkerSub(int id, int maxVelocity, WorkerPub workerPub) {
        this.id = id;
        this.maxVelocity = maxVelocity;
        this.workerPub = workerPub;
    }

    @Override
    public void run() {
        String workerName = Thread.currentThread().getName() + "-Sub";
        try {
            String serverURI = "tcp://mosquitto-broker:9001";
            mqttClient = new MqttClient(serverURI, workerName);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(15);
            options.setUserName("admin");
            options.setPassword("pass_123".toCharArray());

            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable exception) {
                    LOGGER.error("The connection has been lost, exception: {}", exception.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String payload = new String(message.getPayload());
                    LOGGER.info("Incoming message: {}", payload);

                    int velocity = retrieveVelocity(payload);
                    if (velocity > maxVelocity) stopVehicle(id, velocity);
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                }
            });

            mqttClient.connect(options);
            mqttClient.subscribe(TOPIC_SUB);
            LOGGER.info("Subscriber {} connected", workerName);



        } catch (Exception exception) {
            LOGGER.error("Interrupting the thread {}: {}", workerName, exception.getMessage());
        }
    }

    private void stopVehicle(int id, int velocity) {
        LOGGER.info("Current velocity \"{}\" is not allowed! Transport vehicle \"{}\" will be interrupted", velocity, id);
        workerPub.interrupt();
        LOGGER.info("Transport vehicle \"{}\" stopped", id);
    }

    private int retrieveVelocity(String payload) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        TransportVehicle vehicle = mapper.readValue(payload, TransportVehicle.class);
        return vehicle.getVelocity();
    }

}
