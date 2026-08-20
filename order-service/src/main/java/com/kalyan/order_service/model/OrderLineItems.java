package com.kalyan.order_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orders_line_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OrderLineItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
