package com.vekrest.vekconsumer.vekconsumer.configuration;

import com.vekrest.vekconsumer.vekconsumer.entities.Client;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConfig {
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Client> kafkaListenerFactory(
            final KafkaProperties kafkaProperties
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, Client>();
        factory.setConsumerFactory(consumerFactory(kafkaProperties));
        return factory;
    }

    private ConsumerFactory<String, Client> consumerFactory(final KafkaProperties kafkaProperties) {
        DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
        typeMapper.addTrustedPackages("*");
        JsonDeserializer<Client> valueDeserializer = new JsonDeserializer<>(Client.class);
        valueDeserializer.setTypeMapper(typeMapper);
        valueDeserializer.setUseTypeMapperForKey(true);

        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(),
                new StringDeserializer(),
                valueDeserializer
        );
    }
}