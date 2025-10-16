package com.example.apachekafkademo.kafkaservice;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaServiceIncoming {
    private static final Serde<String> STRING_SERDE = Serdes.String();
    private final StreamsBuilder streamsBuilder = new StreamsBuilder();

//    @PostConstruct
//    public void handleIncomingMessage() {
//        KStream<String, String> messageStream = streamsBuilder
//                .stream("mikes-topic", Consumed.with(STRING_SERDE, STRING_SERDE));
//
//        KTable<String, Long> wordCounts = messageStream
//                .mapValues((ValueMapper<String, String>) String::toLowerCase)
//                .mapValues(v -> {
//                    log.info("Received message: {}", v);
//                    return v;
//                })
//                .flatMapValues(value -> Arrays.asList(value.split("\\W+")))
//                .groupBy((key, value) -> value, Grouped.with(STRING_SERDE, STRING_SERDE))
//
//                .count(Materialized.as("counts"));
//        log.info("Created table with word counts: {}", wordCounts);
//    }

}
