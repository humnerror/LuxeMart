package com.luxemart.controller;


import com.luxemart.order.OrderRequest;
import com.luxemart.order.OrderResponse;
import com.luxemart.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @PostMapping("/placeOrder")
    public ResponseEntity<Long> createOrder(
            @RequestBody @Valid OrderRequest request) {

        return ResponseEntity.ok(service.createOrder(request));
    }

    @GetMapping("/exist/{order-id}")
    public ResponseEntity<Boolean> existOrder(@PathVariable("order-id")Long id){
        return ResponseEntity.ok(service.orderExist(id));
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> fetchOrder(@PathVariable("order-id")Long id){
        return ResponseEntity.ok(service.fetchOrderById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> fetchAllOrder(){
        return ResponseEntity.ok(service.fetchAllOrder());
    }





}
