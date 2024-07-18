package com.luxemart.orderLine;

public record OrderLineRequest(
        Long Id,
        Long orderId,
        Long productId,
        Double quantity

) {
}

