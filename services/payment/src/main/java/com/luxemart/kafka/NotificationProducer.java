package com.luxemart.kafka;


import com.luxemart.payment.PaymentNotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProducer {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    private final Logger logger = LoggerFactory.getLogger(NotificationProducer.class);

    public void putMessageInNotification(PaymentNotificationRequest request){

        Message<PaymentNotificationRequest> message = MessageBuilder.withPayload(request)
                .setHeader(KafkaHeaders.TOPIC, "payment-topic")
                .build();

        logger.info("Payment done and the Notification process for the user started :: {}",request);

        kafkaTemplate.send(message);

    }






}
