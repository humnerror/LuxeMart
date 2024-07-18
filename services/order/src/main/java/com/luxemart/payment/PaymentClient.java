package com.luxemart.payment;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "payment-service",
        url= "http://localhost:9994/payment"
)
public interface PaymentClient {

    @PostMapping("/initiatePayment")
    Long makePayment(PaymentRequest request);

}
