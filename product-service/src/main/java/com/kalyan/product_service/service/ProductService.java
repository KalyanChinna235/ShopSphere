package com.kalyan.product_service.service;

import com.kalyan.product_service.dto.ProductRequest;
import com.kalyan.product_service.dto.ProductResponse;
import com.kalyan.product_service.model.Product;
import com.kalyan.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {


    private final ProductRepository productRepository;

    public String createProduct(ProductRequest productRequest) {

        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        Product savedProduct = productRepository.save(product);

        log.info("Product created successfully with id: {}", savedProduct.getId());

        return "Product created successfully with id: " + savedProduct.getId();
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        log.info("Retrieved {} products from the database", products.size());
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
