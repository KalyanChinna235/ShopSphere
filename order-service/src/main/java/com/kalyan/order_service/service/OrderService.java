package com.kalyan.order_service.service;

import com.kalyan.order_service.dto.OrderLineItemsDto;
import com.kalyan.order_service.dto.OrderRequest;
import com.kalyan.order_service.model.Order;
import com.kalyan.order_service.model.OrderLineItems;
import com.kalyan.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;

    public String createOrder(OrderRequest orderRequest) {

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(
                        orderRequest.getOrderLineItemsDtoList()
                                .stream()
                                .map(this::mapToOrderLineItems)
                                .toList()
                )
                .build();

        Order savedOrder = orderRepository.save(order);

        log.info("Order created successfully with order number: {}", savedOrder.getOrderNumber()
        );

        return "Order created successfully with order number: " + savedOrder.getOrderNumber();
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto orderLineItemsDto) {

        return OrderLineItems.builder()
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}