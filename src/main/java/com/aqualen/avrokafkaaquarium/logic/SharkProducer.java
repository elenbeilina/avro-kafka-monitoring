package com.aqualen.avrokafkaaquarium.logic;

import com.aqualen.Shark;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SharkProducer {

    private final KafkaTemplate<String, Shark> producer;

    public void sendShark(Shark shark) {
        ProducerRecord<String, Shark> producerRecord = new ProducerRecord<>("aquarium", shark);

        producer.send(producerRecord);
    }
}
