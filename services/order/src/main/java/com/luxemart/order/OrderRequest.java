package com.luxemart.order;

import com.luxemart.payment.PaymentMethod;
import com.luxemart.product.ProductRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(

        Long id,

        String reference,

        @Positive
        BigDecimal price,

        @NotNull(message = "customer ID should be present")
        @NotBlank(message = "customer ID should be present")
        @NotEmpty(message = "customer ID should be present")
        String customerId,

        @NotNull(message = "Select the appropriate payment method")
        PaymentMethod method,

        @NotNull(message = "select the product to purchase")
        List<ProductRequest> products

) {
}
