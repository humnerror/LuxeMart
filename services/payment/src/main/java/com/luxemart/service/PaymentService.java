package com.luxemart.service;


import com.luxemart.kafka.NotificationProducer;
import com.luxemart.payment.Payment;
import com.luxemart.payment.PaymentMapper;
import com.luxemart.payment.PaymentNotificationRequest;
import com.luxemart.payment.PaymentRequest;
import com.luxemart.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final PaymentMapper mapper;

    private final PaymentRepository repository;

    private final NotificationProducer producer;

    public Long makePayment(PaymentRequest request) {

        Payment entity = repository.save(mapper.toPayment(request));

        producer.putMessageInNotification(

                new PaymentNotificationRequest(
                        request.customer().email(),
                        request.customer().firstName(),
                        request.customer().lastName(),
                        request.reference(),
                        request.paymentMethod(),
                        request.amount()
                )
        );

        return entity.getId();
    }
}
