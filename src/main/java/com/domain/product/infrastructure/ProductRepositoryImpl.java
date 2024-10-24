package com.domain.product.infrastructure;

import com.domain.product.domain.Products;
import com.domain.product.service.port.ProductRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

    private final ProductJpaRepository productJpaRepository;
    private final ProductQueryRepository productQueryRepository;

    @Override
    public List<Products> findAll(String keyword) {
        return productQueryRepository.findAll(keyword);
    }

    @Override
    public Optional<Products> findByPrductId(Long productId) {
        return productJpaRepository.findById(productId);
    }

    @Override
    public List<Products> findAllByUserId(Long userId) {
        return productQueryRepository.findAllByUserId(userId);
    }

    @Override
    public void save(Products product) {
        productJpaRepository.save(product);
    }


}
