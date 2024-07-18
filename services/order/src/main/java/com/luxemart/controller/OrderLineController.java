package com.luxemart.controller;


import com.luxemart.orderLine.OrderLineResponse;
import com.luxemart.service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order-line")
public class OrderLineController {


    private final OrderLineService service;




    @GetMapping("/{order-id}")
    public ResponseEntity<List<OrderLineResponse>> getAllOrderLines(@PathVariable("order-id") Long orderId){


        return ResponseEntity.ok(service.getAllOrderLine(orderId));
    }





}
