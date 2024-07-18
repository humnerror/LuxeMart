package com.luxemart.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductRequest(


        @NotNull(message = "Product Id is required")
        Long productId,

        @Positive(message = "select at least 1 product")
        Double Quantity

) {
}
