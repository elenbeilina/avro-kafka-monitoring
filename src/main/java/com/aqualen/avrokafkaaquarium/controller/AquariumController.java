package com.aqualen.avrokafkaaquarium.controller;

import com.aqualen.Shark;
import com.aqualen.avrokafkaaquarium.logic.SharkProducer;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aquarium")
public class AquariumController {

    private final SharkProducer producer;
    private final Counter sharkCounter;

    public AquariumController(SharkProducer producer, MeterRegistry meterRegistry) {
        this.producer = producer;
        this.sharkCounter = meterRegistry.counter("sharks.send.counter");
    }

    @PutMapping("send-shark")
    public void sendSharkToAquarium() {
        sharkCounter.increment();
        producer.sendShark(Shark.newBuilder()
                .setName("mako")
                .setAverageSize(3)
                .setMaxSpeed(74)
                .setOrder("mackerel shark").build());
    }
}
