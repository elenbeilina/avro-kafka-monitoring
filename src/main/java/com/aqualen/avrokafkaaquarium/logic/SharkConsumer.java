package com.aqualen.avrokafkaaquarium.logic;

import com.aqualen.Shark;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.Metric;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Slf4j
@Getter
@Service
@NoArgsConstructor
public class SharkConsumer {

    private double consumerLag;

    Predicate<Metric> lagMetric = metric -> metric.metricName().name().equals("records-lag-max");

    @KafkaListener(topics = "aquarium_modified", groupId = "log_consumer")

    public void consume(@Payload Shark message, Consumer<String, Shark> consumer) {
        consumerLag = (Double) consumer.metrics().values().stream()
                .filter(lagMetric)
                .map(Metric::metricValue)
                .findFirst().orElseThrow();

        log.info(String.format("Consumed shark: %s", message));
    }
}
