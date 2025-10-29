package com.example.apachekafkademo.kafkaservice.producer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IOTDeviceMeasurement {
    private String deviceId;
    private int temperature;
}
