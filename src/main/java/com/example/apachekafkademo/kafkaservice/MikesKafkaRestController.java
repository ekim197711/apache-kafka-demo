package com.example.apachekafkademo.kafkaservice;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class MikesKafkaRestController {

    private final KafkaProducer<String, String> kafkaProducer;

    @GetMapping("/send-a-message/{message}")
    public String sendMessageToTopic(@PathVariable String message) {
        kafkaProducer.send(new ProducerRecord<>("mikes-topic", "Some-key-for-messages" + UUID.randomUUID(),
                message));
        return "ok";
    }
}
