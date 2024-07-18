package com.luxemart.service;

import com.luxemart.customer.CustomerClient;
import com.luxemart.customer.CustomerResponse;
import com.luxemart.exception.CustomerNotFoundException;
import com.luxemart.exception.OrderNotFoundException;
import com.luxemart.kafka.KafkaProducerService;
import com.luxemart.order.*;
import com.luxemart.orderLine.OrderLineRequest;
import com.luxemart.payment.PaymentClient;
import com.luxemart.payment.PaymentRequest;
import com.luxemart.product.ProductClient;
import com.luxemart.product.ProductRequest;
import com.luxemart.product.ProductResponse;
import com.luxemart.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final OrderRepository orderRepository;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService lineService;
    private final KafkaProducerService kafProService;


    public Long createOrder(OrderRequest request) {

        CustomerResponse customerFromDB = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new CustomerNotFoundException("cannot create order as the customer has no account"));


        List<ProductResponse> productResponses = productClient.fetchAllProducts(request.products());

        Order order = orderRepository.save(mapper.toOrder(request));

        for (ProductRequest requesting : request.products()) {
            lineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            requesting.productId(),
                            requesting.Quantity()
                    )
            );
        }

        Long PaymentId = paymentClient.makePayment(
                new PaymentRequest(
                        request.price(),
                        request.id(),
                        request.method(),
                        request.reference(),
                        customerFromDB
                )
        );


        kafProService.sendOrderObjectToTopic(
                new OrderConfirmation(
                        order.getReference(),
                        order.getTotalAmount(),
                        order.getMethod(),
                        customerFromDB,
                        productResponses
                )
        );

        return order.getId();
    }

    public Boolean orderExist(Long id) {
        return orderRepository.existsById(id);
    }

    public OrderResponse fetchOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Given Order Id is not found in the database"));

        return mapper.toOrderResponse(order);
    }

    public List<OrderResponse> fetchAllOrder() {

        List<Order> orderList = orderRepository.findAll();

        return orderList.stream()
                .map(mapper::toOrderResponse)
                .toList();
    }
}
