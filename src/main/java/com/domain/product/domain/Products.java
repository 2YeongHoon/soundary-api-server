package com.domain.product.domain;

import com.domain.common.entity.RootEntity;
import com.domain.instrument.domain.Instrument;
import com.domain.product.enums.ProductType;
import com.domain.user.domain.UserProduct;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 상품 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "products")
public class Products extends RootEntity {

    @Comment("사용자 상품")
    @OneToMany(mappedBy = "products",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Set<UserProduct> userProducts = new LinkedHashSet<>();

    @OneToOne(mappedBy = "products",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Instrument instrument;

    @Comment("상품타입")
    @Enumerated(EnumType.STRING)
    @Column(name = "product_type", nullable = false)
    private ProductType productType;

    @Comment("상품명")
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Comment("상품가격")
    @Column(name = "price")
    private Long price;

    @Comment("상품이미지 s3 파일명")
    @Column(name = "image_name")
    private String imageName;

    @Comment("상품아이콘 s3 파일명")
    @Column(name = "icon_name")
    private String iconName;


    public void addUserProduct(UserProduct userProduct) {
        this.userProducts.add(userProduct);
    }
}
