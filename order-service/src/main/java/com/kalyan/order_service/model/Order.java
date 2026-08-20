package com.kalyan.order_service.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItems;
}
