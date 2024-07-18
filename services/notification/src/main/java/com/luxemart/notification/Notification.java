package com.luxemart.notification;


import com.luxemart.order.OrderConfirmation;
import com.luxemart.payment.PaymentConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notification {

    @Id
    private Long id;

    private NotificationType notificationType;

    private LocalDateTime dateTime;

    private OrderConfirmation orderConfirmation;

    private PaymentConfirmation paymentConfirmation;

}
