package com.microservice.producer.service.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.producer.model.TempSensor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class Producer {

    @Value("${message.topic.name}")
    private String topicName;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public Producer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public String sendMessage(TempSensor tempSensor) throws JsonProcessingException {
        String orderAsMessage = objectMapper.writeValueAsString(tempSensor);
        kafkaTemplate.send(topicName, orderAsMessage);

        log.info("temperature sensor produced {}", orderAsMessage);

        return "message sent";
    }
}