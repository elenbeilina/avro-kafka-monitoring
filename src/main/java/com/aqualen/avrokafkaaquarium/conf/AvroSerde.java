package com.aqualen.avrokafkaaquarium.conf;

import com.aqualen.Shark;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.subject.TopicRecordNameStrategy;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.kafka.common.serialization.Serde;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Getter
@Component
@NoArgsConstructor
public class AvroSerde {

    @Value("${registry.url}")
    private String registry;
    private Serde<Shark> sharkSerde;

    @PostConstruct
    private void configureSerde() {
        sharkSerde = new SpecificAvroSerde<>();
        sharkSerde.configure(getConfig(), false);
    }

    private Map<String, Object> getConfig() {
        final Map<String, Object> serdeConfig = new HashMap<>();

        serdeConfig.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, registry);
        serdeConfig.put(AbstractKafkaAvroSerDeConfig.AUTO_REGISTER_SCHEMAS, true);
        serdeConfig.put(AbstractKafkaAvroSerDeConfig.VALUE_SUBJECT_NAME_STRATEGY,
                TopicRecordNameStrategy.class.getName());
        serdeConfig.put(AbstractKafkaAvroSerDeConfig.KEY_SUBJECT_NAME_STRATEGY,
                TopicRecordNameStrategy.class.getName());

        return serdeConfig;
    }

}