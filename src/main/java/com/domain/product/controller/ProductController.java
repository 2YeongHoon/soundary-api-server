package com.domain.product.controller;

import com.domain.common.dto.SuccessResponse;
import com.domain.common.utils.SuccessResponseUtils;
import com.domain.product.controller.dto.response.RetrieveProductResponse;
import com.domain.product.service.ProductManagementService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/products")
@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductManagementService productManagementService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<RetrieveProductResponse>>> retrieve(@RequestParam String keywords) {
        return SuccessResponseUtils.successResponse(productManagementService.retrieve(keywords));
    }

    @GetMapping("/{product-id}")
    public ResponseEntity<SuccessResponse<RetrieveProductResponse>> getById(@PathVariable("product-id") Long productId) {
        return SuccessResponseUtils.successResponse(productManagementService.getById(productId));
    }

    @GetMapping("mine")
    public ResponseEntity<SuccessResponse<List<RetrieveProductResponse>>> getMyProducts() {
        return SuccessResponseUtils.successResponse(productManagementService.getMyProducts());
    }
}
