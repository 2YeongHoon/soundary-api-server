package com.domain.product.infrastructure;

import com.domain.product.domain.Products;
import com.domain.product.domain.QProducts;
import com.domain.user.domain.QUserProduct;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QProducts qProducts = QProducts.products;
    private final QUserProduct qUserProduct = QUserProduct.userProduct;

    public List<Products> findAll(String keyword) {
        return queryFactory
            .select(qProducts)
            .from(qProducts)
            .where(qProducts.productName.toLowerCase().contains(keyword.toLowerCase()))
            .fetch();
    }

    public List<Products> findAllByUserId(Long userId) {
        return queryFactory
            .select(qProducts)
            .from(qProducts)
            .join(qProducts.userProducts, qUserProduct).fetchJoin()
            .where(qUserProduct.user.id.eq(userId))
            .fetch();
    }
}
