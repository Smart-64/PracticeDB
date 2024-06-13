package ru.velocity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.velocity.service.WorkerPub;
import ru.velocity.service.WorkerSub;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
public class TransportVehicleRest {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransportVehicleRest.class);

    public List<WorkerPub> workerPubList = new ArrayList<>();
    public List<WorkerSub> workerSubList = new ArrayList<>();

    private final Random random = new Random();
    @GetMapping("workerPub/maxVelocity/{maxVelocity}")
    public String setVelocity(@PathVariable("maxVelocity") int maxVelocity) {
        LOGGER.info("Create new vehicle with maxVelocity {}", maxVelocity);
        int id = random.nextInt(1000);

        WorkerPub workerPub = new WorkerPub(id, maxVelocity);
        WorkerSub workerSub = new WorkerSub(id, maxVelocity, workerPub);
        workerPub.start();
        workerSub.start();

        workerPubList.add(workerPub);
        workerSubList.add(workerSub);
        return "Создан новый датчик максимальной скорости " + maxVelocity + " км/ч";
    }
    @GetMapping("/workerList")
    public String getWorkerList() {
        StringBuilder strPub = new StringBuilder("[Pub:");
        for (WorkerPub worker: workerPubList) {
            strPub.append(worker + ", ");
        }
        if (strPub.length() > 2) strPub.setLength(strPub.length() - 2);
        strPub.append("]");

        StringBuilder strSub = new StringBuilder("[Sub:");
        for (WorkerSub worker: workerSubList) {
            strSub.append(worker + ", ");
        }
        if (strSub.length() > 2) strSub.setLength(strSub.length() - 2);
        strSub.append("]");

        return strPub.toString() + strSub.toString();
    }
    //influx -database 'measureVelocityDataBase' -execute 'select * from mqtt_consumer' -format 'json' -pretty
}
