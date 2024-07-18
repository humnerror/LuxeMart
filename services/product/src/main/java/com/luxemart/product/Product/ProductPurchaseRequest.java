package com.luxemart.product.Product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(

        @NotNull(message = "product id is required")
        Long productId,

        @Positive(message = "quantity should be more than 1")
        Double Quantity

) { }
