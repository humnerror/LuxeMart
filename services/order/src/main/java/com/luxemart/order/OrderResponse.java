package com.luxemart.order;

import com.luxemart.payment.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Long id,

        String reference,

        BigDecimal price,

        String customerId,

        PaymentMethod method
) {
}
