package com.luxemart.order;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMapper {


    public Order toOrder(OrderRequest request) {
        return Order.builder()
                .id(request.id())
                .customer(request.customerId())
                .method(request.method())
                .reference(request.reference())
                .totalAmount(request.price())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getCustomer(),
                order.getMethod()
        );
    }
}
