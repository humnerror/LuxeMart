package com.luxemart.order;

import com.luxemart.payment.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,

        String reference,

        BigDecimal price,

        Long customerId,

        PaymentMethod method
) {
}
