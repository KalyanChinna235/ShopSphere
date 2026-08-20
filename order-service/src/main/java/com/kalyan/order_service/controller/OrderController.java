package com.kalyan.order_service.controller;

import com.kalyan.order_service.dto.OrderRequest;
import com.kalyan.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderReuest){

        String order = orderService.createOrder(orderReuest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
