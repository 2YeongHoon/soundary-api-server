package com.domain.user.domain;

import com.domain.common.entity.RootEntity;
import com.domain.product.domain.Products;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 사용자 상품 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_product")
public class UserProduct extends RootEntity {

    @Comment("사용자")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Comment("상품")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Products products;

    private UserProduct(User user, Products products) {
        this.user = user;
        this.products = products;
    }

    public static UserProduct of(User user, Products products) {
        return new UserProduct(user, products);
    }

}
