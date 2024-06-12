package ru.velocity.util;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Worker extends Thread {

    private static final Logger LOGGER = LoggerFactory.getLogger(Worker.class);
    private MqttClient mqttClient;
    private final static String TOPIC_PUB = "pub_topic_velocity";
    private final static String TOPIC_SUB = "sub_topic_velocity";
    private final int id_TransportVehicle;
    private final int velocity;



    public Worker(int id_TransportVehicle, int velocity) {
        this.id_TransportVehicle = id_TransportVehicle;
        this.velocity = velocity;
    }

    @Override
    public void run() {
        String workerName = Thread.currentThread().getName();
        try {
            String serverURI = "tcp://mosquitto-broker:9001";
            mqttClient = new MqttClient(serverURI, workerName);

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(15);
            options.setUserName("admin");
            options.setPassword("pass_123".toCharArray());

            mqttClient.connect(options);
            LOGGER.info("Worker {} connected", workerName);

            send

        } catch (Exception exception) {
            LOGGER.error("Interrupting the thread {}: {}", workerName, exception.getMessage());
        }
    }
}
