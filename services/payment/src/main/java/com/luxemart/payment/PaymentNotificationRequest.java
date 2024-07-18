package com.luxemart.payment;

import java.math.BigDecimal;

public record PaymentNotificationRequest(

        String customerMail,

        String firstname,

        String lastname,

        String orderReference,

        PaymentMethod paymentMethod,

        BigDecimal amount


) {
}
