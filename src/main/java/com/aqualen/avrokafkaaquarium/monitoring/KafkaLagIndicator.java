package com.aqualen.avrokafkaaquarium.monitoring;

import com.aqualen.avrokafkaaquarium.logic.SharkConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaLagIndicator implements HealthIndicator {

    private static final Double CONSUMER_LAG_LEVEL = 300000.0;
    private static final String CONSUMER_INDICATOR = "Consumer lag indicator";
    private final SharkConsumer consumer;

    @Override
    public Health health() {
        if (consumer.getConsumerLag() > CONSUMER_LAG_LEVEL) {
            return Health.down()
                    .withDetail(CONSUMER_INDICATOR, "Higher then level: " + CONSUMER_LAG_LEVEL)
                    .build();
        } else {
            return Health.up().withDetail(CONSUMER_INDICATOR, "Healthy").build();
        }
    }
}
