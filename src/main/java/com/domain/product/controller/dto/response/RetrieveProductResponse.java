package com.domain.product.controller.dto.response;

import com.domain.product.domain.Products;
import com.domain.product.enums.ProductType;
import lombok.Getter;

@Getter
public class RetrieveProductResponse {

    private final Long productId;
    private final ProductType productType;
    private final String name;
    private final String imageName;
    private final String iconName;
    private final Long price;

    private RetrieveProductResponse(Products product) {
        this.productId = product.getId();
        this.productType = product.getProductType();
        this.name = product.getProductName();
        this.imageName = product.getImageName();
        this.iconName = product.getIconName();
        this.price = product.getPrice();
    }

    public static RetrieveProductResponse from(Products product) {
        return new RetrieveProductResponse(product);
    }
}
