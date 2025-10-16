package com.example.apachekafkademo.kafkaservice;

import org.apache.kafka.common.serialization.Serdes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;

import java.util.HashMap;
import java.util.Map;

import static org.apache.kafka.streams.StreamsConfig.*;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaConfig {
    
        @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
        KafkaStreamsConfiguration kStreamsConfig() {
            Map<String, Object> props = Map.of(
                    APPLICATION_ID_CONFIG, "mikes-streams",
                    BOOTSTRAP_SERVERS_CONFIG, "localhost:9092",
                    DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName(),
                    DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName()
            );

            return new KafkaStreamsConfiguration(props);
        }

        // other config
    }
}
