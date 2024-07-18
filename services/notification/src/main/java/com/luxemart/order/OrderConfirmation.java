package com.luxemart.order;

import com.luxemart.customer.CustomerResponse;
import com.luxemart.payment.PaymentMethod;
import com.luxemart.product.ProductResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(

        String reference,

        BigDecimal totalAmount,

        PaymentMethod method,

        CustomerResponse customerResponse,

        List<ProductResponse> productResponses


) { }
