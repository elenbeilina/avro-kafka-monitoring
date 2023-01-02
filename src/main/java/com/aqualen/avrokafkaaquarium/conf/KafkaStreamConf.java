package com.aqualen.avrokafkaaquarium.conf;

import com.aqualen.Shark;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;

import java.util.Objects;

@Configuration
@EnableKafkaStreams
@RequiredArgsConstructor
public class KafkaStreamConf {

    private final AvroSerde avroSerde;

    @Bean
    KStream<String, Shark> defineStream(StreamsBuilder builder) {
        final var sharkSerde = avroSerde.getSharkSerde();

        KStream<String, Shark> stream = builder
                .stream("aquarium",
                        Consumed.with(Serdes.String(), sharkSerde));

        stream
                .filter(((key, value) -> Objects.nonNull(value)))
                .map((key, value) -> (new KeyValue<>(value.getOrder(), value)))
                .to("aquarium_modified", Produced.with(Serdes.String(), sharkSerde));

        return stream;
    }
}