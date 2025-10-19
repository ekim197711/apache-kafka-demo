package com.example.apachekafkademo.kafkaservice.adminstuff;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTopicOnStartupService {
    private final KafkaAdmin kafkaAdmin;

    public void createTopicIfNotExists() {
        kafkaAdmin.createOrModifyTopics(new NewTopic("mikes-topic", 1, (short) 1));
    }
}
