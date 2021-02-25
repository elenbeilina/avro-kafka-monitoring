package com.aqualen.avrokafkaaquarium.logic;

import com.aqualen.Shark;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SharkConsumer {

    @KafkaListener(topics = "aquarium", groupId = "russian")
    public void consume(Shark message) {
        log.info(String.format("Consumed shark: %s", message));
    }
}
