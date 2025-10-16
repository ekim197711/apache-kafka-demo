package com.example.apachekafkademo.kafkaservice;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class KafkaService {
    private static final Serde<String> STRING_SERDE = Serdes.String();
    private final StreamsBuilder streamsBuilder = new StreamsBuilder();

    public void send() {
        KStream<String, String> messageStream = streamsBuilder
                    .stream("mikes-topic", Consumed.with(STRING_SERDE, STRING_SERDE));

                KTable<String, Long> wordCounts = messageStream
                .mapValues((ValueMapper<String, String>) String::toLowerCase)
                .flatMapValues(value -> Arrays.asList(value.split("\\W+")))
                .groupBy((key, value) -> value, Grouped.with(STRING_SERDE, STRING_SERDE))
                .count(Materialized.as("counts"));

        }
    }
}
