package com.luxemart.service;


import com.luxemart.orderLine.OrderLine;
import com.luxemart.orderLine.OrderLineMapper;
import com.luxemart.orderLine.OrderLineRequest;
import com.luxemart.orderLine.OrderLineResponse;
import com.luxemart.repository.OrderLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository lineRepository;

    private final OrderLineMapper lineMapper;

    public Long saveOrderLine(OrderLineRequest orderLineRequest) {

        OrderLine orderLine = lineRepository.save(lineMapper.toOrderLine(orderLineRequest));

        return orderLine.getId();
    }

    public List<OrderLineResponse> getAllOrderLine(Long id) {

        List<OrderLine> orderLines = lineRepository.findAllByOrderId(id);

        return orderLines.stream()
                .map(lineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
