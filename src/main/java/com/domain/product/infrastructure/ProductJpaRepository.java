package com.domain.product.infrastructure;


import com.domain.product.domain.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductJpaRepository extends JpaRepository<Products, Long> {

}
