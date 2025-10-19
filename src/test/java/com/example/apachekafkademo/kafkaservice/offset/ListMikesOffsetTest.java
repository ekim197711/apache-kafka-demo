package com.example.apachekafkademo.kafkaservice.offset;

import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.PartitionInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ListMikesOffsetTest {

    @Autowired
    KafkaAdminClient adminClient;

    @Autowired
    KafkaAdmin admin;

    @Autowired
    KafkaProducer<String, String> producer;

    @Test
    public void listOffsets() {
        List<PartitionInfo> partitionInfos = producer.partitionsFor("mikes-topic");
        PartitionInfo partitionInfo = partitionInfos.getFirst();
        System.out.println(partitionInfo);

        System.out.println(adminClient.listConsumerGroupOffsets("mikes-group"));

    }
}
