package com.microservice.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.producer.model.TempSensor;
import com.microservice.producer.service.TempSensorService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/sensor")
public class TempSensorController {

    private final TempSensorService tempSensorService;

    @Autowired
    public TempSensorController(TempSensorService tempSensorService) {
        this.tempSensorService = tempSensorService;
    }

    @PostMapping
    public String createTempSensor(@RequestBody TempSensor tempSensor) throws JsonProcessingException {
        log.info("create food order request received");
        return tempSensorService.createTempSensor(tempSensor);
    }
}