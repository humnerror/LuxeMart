package com.luxemart.product.Product;

import java.math.BigDecimal;

public record ProductResponse(
         Long id,
         String name,
         String description,
         Double availableQuantity,
         BigDecimal price,
         Long category_id,
         String category_name,
         String category_description
) { }
