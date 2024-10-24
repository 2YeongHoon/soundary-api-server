package com.domain.product.service.port;

import com.domain.product.domain.Products;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<Products> findAll(String keyword);
    Optional<Products> findByPrductId(Long productId);
    List<Products> findAllByUserId(Long userId);
    void save(Products product);
}
