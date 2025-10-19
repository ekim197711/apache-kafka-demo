package com.example.apachekafkademo.kafkaservice.producer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IOTDeviceMeasurement {
    private String deviceId;
    private int temperature;
}
