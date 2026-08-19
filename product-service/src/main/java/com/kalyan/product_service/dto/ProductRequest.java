package com.kalyan.product_service.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class ProductRequest {

    private String name;
    private String description;
    private BigDecimal price;
}
