package com.example.apachekafkademo.kafkaservice.offset;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AlterConsumerGroupOffsetsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
@Slf4j
@RequiredArgsConstructor
public class OffsetService {
    private final KafkaAdminClient apacheKafkaAdmin;
    private final KafkaAdmin springKafkaAdmin;


    public Map<TopicPartition, OffsetAndMetadata> listOffsets() {
        String groupName = "mikes-group";
        try {
            Map<TopicPartition, OffsetAndMetadata> offsets = apacheKafkaAdmin.listConsumerGroupOffsets(
                            groupName)
                    .partitionsToOffsetAndMetadata().get();
            offsets.forEach((k, v) -> log.info("tp {} offset {}", k.toString(), v.offset()));
            return offsets;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<TopicPartition, OffsetAndMetadata> setNewOffset(long offset) {
        String groupName = "mikes-group";
        try {
            Map<TopicPartition, OffsetAndMetadata> offsets = apacheKafkaAdmin.listConsumerGroupOffsets(
                    groupName).partitionsToOffsetAndMetadata().get();
            for (Map.Entry<TopicPartition, OffsetAndMetadata> e : offsets.entrySet()) {
                offsets.put(e.getKey(), new OffsetAndMetadata(offset));
            }
            AlterConsumerGroupOffsetsResult result = apacheKafkaAdmin.alterConsumerGroupOffsets(
                    groupName, offsets);
            log.info("Altering offset Result: {}", result.all().get());
            return listOffsets();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }
}
