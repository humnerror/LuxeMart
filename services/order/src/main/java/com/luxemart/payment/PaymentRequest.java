package com.luxemart.payment;

import com.luxemart.customer.CustomerResponse;

import java.math.BigDecimal;

public record PaymentRequest(

        BigDecimal amount,

        Long OrderId,

        PaymentMethod paymentMethod,

        String reference,

        CustomerResponse customer

) {
}
