package com.luxemart.product.Product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(

        Long id,

        @NotNull(message = "Name is mandatory")
        String name,

        @NotNull(message = "Description is mandatory")
        String description,

        @Positive(message = "Available quantity should be positive")
        Double availableQuantity,

        @Positive(message = "Price should be positive")
        BigDecimal price,

        @Positive(message = "Category is required")
        Long category

) {
}
