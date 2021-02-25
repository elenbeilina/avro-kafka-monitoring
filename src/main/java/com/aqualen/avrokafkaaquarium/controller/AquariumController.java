package com.aqualen.avrokafkaaquarium.controller;

import com.aqualen.Shark;
import com.aqualen.avrokafkaaquarium.logic.SharkProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aquarium")
@RequiredArgsConstructor
public class AquariumController {

    private final SharkProducer producer;

    @PutMapping("send-shark")
    public void sendSharkToAquarium() {
        producer.sendShark(Shark.newBuilder()
                .setName("mako")
                .setAverageSize(3)
                .setMaxSpeed(74)
                .setOrder("mackerel shark").build());
    }
}
