package ru.velocity.service;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class WorkerPub extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerPub.class);
    private MqttClient mqttClient;
    private final static String TOPIC_PUB = "topic_velocity";
    private final int maxVelocity;
    private final int id_TransportVehicle;




    public WorkerPub(int id_TransportVehicle, int maxVelocity) {
        this.maxVelocity = maxVelocity;
        this.id_TransportVehicle = id_TransportVehicle;
    }

    @Override
    public void run() {
        String workerName = Thread.currentThread().getName() + "-Pub";
        try {
            String serverURI = "tcp://mosquitto-broker:9001";
            mqttClient = new MqttClient(serverURI, workerName);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(15);
            options.setUserName("admin");
            options.setPassword("admin".toCharArray());

            mqttClient.connect(options);
            LOGGER.info("Worker {} connected", workerName);

            generateData(maxVelocity);

        } catch (Exception exception) {
            LOGGER.error("Interrupting the thread {}: {}", workerName, exception.getMessage());
        }
    }

    private void generateData(int maxVelocity) throws MqttException, InterruptedException {
        for (int i = 0; i < maxVelocity + 5; i++) {
            Thread.sleep(1000);
            MqttMessage message = createMessage(i);
            message.setRetained(true);
            mqttClient.publish(TOPIC_PUB, message);
            LOGGER.info("Send message: {}", message);
            Thread.sleep(1000);
        }
    }

    private MqttMessage createMessage(int i) {
        String request = "{\"id_TransportVehicle\": " + id_TransportVehicle + ", \"velocity\": " + i + "}";

        return new MqttMessage(request.getBytes());
    }
}
