package com.luxemart.configuration;


import com.luxemart.order.OrderConfirmation;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {


    @Bean
    public NewTopic OrderTopic() {
        return TopicBuilder
                .name("order-topic")
                .build();
    }

    @Bean
    public KafkaTemplate<String, OrderConfirmation> getTemplate(){
        return new KafkaTemplate<>(getProducerFactory());
    }

    public ProducerFactory<String, OrderConfirmation> getProducerFactory(){
        Map<String, Object> producerMap = new HashMap<>();
        producerMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"http://localhost:9092");
        producerMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(producerMap);
    }




}
