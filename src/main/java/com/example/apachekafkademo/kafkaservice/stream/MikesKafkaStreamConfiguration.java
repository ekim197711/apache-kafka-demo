package com.example.apachekafkademo.kafkaservice.stream;

import com.example.apachekafkademo.kafkaservice.adminstuff.CreateTopicOnStartupService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;

@Configuration
@EnableKafka
@EnableKafkaStreams
@Slf4j
public class MikesKafkaStreamConfiguration {

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    KafkaStreamsConfiguration kStreamsConfig() {
        Map<String, Object> props = Map.of(
                APPLICATION_ID_CONFIG, "mikes-streams2",
                BOOTSTRAP_SERVERS_CONFIG, "localhost:9092,127.0.0.1:9092",
                DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName(),
                DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName()
        );
        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public KStream<String, String> topologyBuilder(StreamsBuilder streamsBuilder,
                                                   CreateTopicOnStartupService createTopicOnStartupService) {
        createTopicOnStartupService.createTopicIfNotExists();
        KStream<String, String> stream = streamsBuilder.stream("mikes-topic",
                Consumed.with(Serdes.String(), Serdes.String()));
        KStream<String, String> stream1 = stream.mapValues((key, value) -> {
            log.info("Received message: {}, {}", key, value);
            return value;
        });
        stream1.to("mikes-topic-out");
        return stream1;
    }
}

