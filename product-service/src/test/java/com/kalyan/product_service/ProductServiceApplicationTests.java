package com.kalyan.product_service;

import com.kalyan.product_service.dto.ProductRequest;
import com.kalyan.product_service.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

    @Container
    static MongoDBContainer mongoDBContainer =
            new MongoDBContainer("mongo:8");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductRepository productRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add(
                "spring.mongodb.uri",
                mongoDBContainer::getReplicaSetUrl
        );
    }

    @Test
    void shouldCreateProduct() throws Exception {

        ProductRequest productRequest = ProductRequest.builder()
                .name("iPhone 14")
                .description("Latest Apple iPhone 14 with advanced features")
                .price(BigDecimal.valueOf(999.99))
                .build();

        String productRequestJson =
                objectMapper.writeValueAsString(productRequest);

        mockMvc.perform(
                        post("/api/product/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(productRequestJson)
                )
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, productRepository.findAll().size());
    }

}
