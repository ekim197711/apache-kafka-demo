package com.example.apachekafkademo.kafkaservice.offset;

import com.example.apachekafkademo.kafkaservice.adminstuff.CreateTopicOnStartupService;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OffsetServiceTest {
    @Autowired
    OffsetService offsetService;
    @Autowired
    CreateTopicOnStartupService createTopicOnStartupService;

    @Test
    void createMessages() {
        createTopicOnStartupService.createRandomMessages();
    }

    @Test
    void listOffsets() {
        Map<TopicPartition, OffsetAndMetadata> offsets = offsetService.listOffsets();
        Assertions.assertThat(offsets).isNotEmpty();
    }

    @Test
    void changeOffset() {
        Map<TopicPartition, OffsetAndMetadata> offsets = offsetService.setNewOffset(15);
        Assertions.assertThat(offsets).isNotEmpty();
    }
}