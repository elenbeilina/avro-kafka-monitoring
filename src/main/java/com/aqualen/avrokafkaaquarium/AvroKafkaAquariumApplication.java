package com.aqualen.avrokafkaaquarium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class AvroKafkaAquariumApplication {

    public static void main(String[] args) {
        SpringApplication.run(AvroKafkaAquariumApplication.class, args);
    }

}
