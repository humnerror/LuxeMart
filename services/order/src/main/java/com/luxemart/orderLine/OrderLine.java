package com.luxemart.orderLine;

import com.luxemart.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLine {


    @Id
    @GeneratedValue
    private Long id;

    private Double quantity;

    private Long product_id;

    @ManyToOne()
    @JoinColumn(name = "order_id")
    private Order order;


}
