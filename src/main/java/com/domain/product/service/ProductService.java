package com.domain.product.service;

import com.domain.common.enums.Errors;
import com.domain.common.exception.BaseNotFoundException;
import com.domain.product.controller.dto.response.RetrieveProductResponse;
import com.domain.product.domain.Products;
import com.domain.product.service.port.ProductRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<RetrieveProductResponse> retrieve(String keyword) {
        List<Products> productList = productRepository.findAll(keyword);

        List<RetrieveProductResponse> result = productList.stream()
            .map(this::toRetrieveProductResponse)
            .collect(Collectors.toList());

        return result;
    }

    @Transactional(readOnly = true)
    public List<Products> findAllByUserId(Long userId) {
        return productRepository.findAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    public Products getByProductId(Long productId) {
        return productRepository.findByPrductId(productId)
            .orElseThrow(() -> new BaseNotFoundException(Errors.NOT_FOUND_PRODUCT));
    }

    @Transactional
    public void save(Products product) {
        productRepository.save(product);
    }

    private RetrieveProductResponse toRetrieveProductResponse(Products products) {
        return RetrieveProductResponse.from(products);
    }
}
