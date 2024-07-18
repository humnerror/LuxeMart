package com.luxemart.kafka;


import com.luxemart.mail.EmailService;
import com.luxemart.notification.Notification;
import com.luxemart.notification.NotificationType;
import com.luxemart.order.OrderConfirmation;
import com.luxemart.payment.PaymentConfirmation;
import com.luxemart.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final Logger logger = LoggerFactory.getLogger(NotificationConsumer.class);

    private final NotificationRepository repository;

    private final EmailService emailService;


    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessMessage(PaymentConfirmation paymentConfirmation) throws MessagingException {

        logger.info(String.format("Payment Successfully done :: %s",paymentConfirmation));

        repository.save(
                Notification.builder()
                        .dateTime(LocalDateTime.now())
                        .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                        .paymentConfirmation(paymentConfirmation)
                        .build()
        );

        emailService.sendPaymentSuccessMail(
                paymentConfirmation.customerMail(),
                paymentConfirmation.firstname()+" "+paymentConfirmation.lastname(),
                paymentConfirmation.amount(),
                paymentConfirmation.orderReference()
        );

    }


    @KafkaListener(topics = "payment-topic")
    public void consumeOrderConfirmationMessage(OrderConfirmation orderConfirmation) throws MessagingException {

        logger.info(String.format("Payment Successfully done :: %s",orderConfirmation));

        repository.save(
                Notification.builder()
                        .dateTime(LocalDateTime.now())
                        .notificationType(NotificationType.ORDER_CONFIRMATION)
                        .orderConfirmation(orderConfirmation)
                        .build()
        );

        emailService.sendOrderConfirmationMail(
                orderConfirmation.customerResponse().email(),
                orderConfirmation.customerResponse().firstName()+" "+orderConfirmation.customerResponse().lastName(),
                orderConfirmation.totalAmount(),
                orderConfirmation.reference(),
                orderConfirmation.productResponses()
        );


    }




}
