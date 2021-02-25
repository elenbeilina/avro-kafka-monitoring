package com.aqualen.avrokafkaaquarium.conf;

import com.aqualen.Shark;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

import static org.apache.kafka.clients.producer.ProducerConfig.*;

@Configuration
public class KafkaProducerConf {

    @Value("${bootstrap.url}")
    private String bootstrapServers;
    @Value("${registry.url}")
    private String registry;

    @Bean
    public Properties generate() {
        Properties properties = new Properties();

        // Kafka Properties
        properties.setProperty(BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ACKS_CONFIG, "all");
        properties.setProperty(RETRIES_CONFIG, "10");
        // Avro properties
        properties.setProperty(KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.setProperty("schema.registry.url", registry);

        return properties;
    }

    @Bean
    public KafkaProducer<String, Shark> kafkaProducer() {
        return new KafkaProducer<>(generate());
    }
}
