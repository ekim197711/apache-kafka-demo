package com.example.apachekafkademo.kafkaservice.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MikesKafkaRestController {

    private final KafkaProducer<String, String> kafkaProducer;

    @PostMapping("/send-a-message/")
    public String sendMessageToTopic(@RequestBody IOTDeviceMeasurement message) throws JsonProcessingException {
        log.info("Sending message to topic: {}", message);
        kafkaProducer.send(new ProducerRecord<>("mikes-topic", message.getDeviceId(),
                new ObjectMapper().writeValueAsString(message)));
        return "ok";
    }
}
