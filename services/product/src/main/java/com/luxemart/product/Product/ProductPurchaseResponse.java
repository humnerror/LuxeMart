package com.luxemart.product.Product;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
         Long id,
         String name,
         String description,
         BigDecimal price,
         Double availableQuantity
) {
}
