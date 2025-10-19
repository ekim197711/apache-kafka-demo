package com.example.apachekafkademo.kafkaservice;

import com.example.apachekafkademo.kafkaservice.producer.IOTDeviceMeasurement;
import com.example.apachekafkademo.kafkaservice.producer.MikesKafkaRestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MikesKafkaRestControllerTest {
    @Autowired
    MikesKafkaRestController mikesKafkaRestController;

    @Test
    public void testSendAMessage() throws JsonProcessingException {
        mikesKafkaRestController.sendMessageToTopic(
                new IOTDeviceMeasurement("device1", 21));
        mikesKafkaRestController.sendMessageToTopic(
                new IOTDeviceMeasurement("device1", 23));
    }
}