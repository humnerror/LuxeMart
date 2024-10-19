package com.luxemart.kafka;


import com.luxemart.order.OrderConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerService {

    private final KafkaTemplate<String,OrderConfirmation> kafkaTemplate;


    public void sendOrderObjectToTopic(OrderConfirmation orderConfirmation) {
        log.info("Sending notification with body = < {} >", orderConfirmation);
        Message<OrderConfirmation> message = MessageBuilder.withPayload(orderConfirmation)
                .setHeader(KafkaHeaders.TOPIC, "order-topic")
                .build();

        kafkaTemplate.send(message);

    }
}
