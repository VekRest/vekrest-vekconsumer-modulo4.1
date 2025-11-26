package com.vekrest.vekconsumer.vekconsumer.consumer;

import com.vekrest.vekconsumer.vekconsumer.entities.Client;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.retrytopic.TopicSuffixingStrategy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Component;

@Component
public class ClientRegisteredConsumer {
    @RetryableTopic(
            autoCreateTopics = "false",
            backoff = @Backoff(
                    delay = 15000,
                    multiplier = 2.0,
                    maxDelay = 54000
            ),
            topicSuffixingStrategy = TopicSuffixingStrategy.SUFFIX_WITH_INDEX_VALUE
    )
    @KafkaListener(
            topics = "${spring.kafka.topic.client.registered}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void listener(@Payload ConsumerRecord<String, Client> consumerRecord) {
        System.out.println("KEY: " + consumerRecord.key());
        System.out.println("VALUE: " + consumerRecord.value());
    }
}