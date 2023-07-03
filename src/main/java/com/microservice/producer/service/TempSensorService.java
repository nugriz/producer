package com.microservice.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservice.producer.model.TempSensor;
import com.microservice.producer.service.producer.Producer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempSensorService {

    private final Producer producer;

    @Autowired
    public TempSensorService(Producer producer) {
        this.producer = producer;
    }

    public String createTempSensor(TempSensor tempSensor) throws JsonProcessingException {
        return producer.sendMessage(tempSensor);
    }
}