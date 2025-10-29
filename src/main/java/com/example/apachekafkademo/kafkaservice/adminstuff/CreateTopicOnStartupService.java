package com.example.apachekafkademo.kafkaservice.adminstuff;

import com.example.apachekafkademo.kafkaservice.producer.IOTDeviceMeasurement;
import com.example.apachekafkademo.kafkaservice.producer.MikesKafkaRestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateTopicOnStartupService {
    private final KafkaAdmin kafkaAdmin;
    private final MikesKafkaRestController mikesKafkaRestController;

    public void createTopicIfNotExists() throws JsonProcessingException {
        kafkaAdmin.createOrModifyTopics(new NewTopic("mikes-topic", 1, (short) 1));
    }

    public void createRandomMessages() {
        for (int i = 0; i < 1000; i++) {
            try {
                mikesKafkaRestController.sendMessageToTopic(
                        IOTDeviceMeasurement.builder().deviceId("" + UUID.randomUUID())
                                .temperature(new Random().nextInt())
                                .build());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
