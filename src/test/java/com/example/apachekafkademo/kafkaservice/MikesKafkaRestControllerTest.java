package com.example.apachekafkademo.kafkaservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MikesKafkaRestControllerTest {
    @Autowired
    MikesKafkaRestController mikesKafkaRestController;

    @Test
    public void testSendAMessage() {
        mikesKafkaRestController.sendMessageToTopic("Apples tastes great");
    }
}