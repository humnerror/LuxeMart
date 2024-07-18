package com.luxemart.payment;

import com.luxemart.customer.Customer;

import java.math.BigDecimal;

public record PaymentRequest(

       Long id,

       BigDecimal amount,

       PaymentMethod paymentMethod,

       Customer customer,

       String reference,

       Long orderId

) {
}
