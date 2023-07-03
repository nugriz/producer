package com.microservice.producer.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class TempSensor {

    private String temp;
    
    @JsonCreator
    public TempSensor(@JsonProperty("temp") String temp) {
        this.temp = temp;
    }
}