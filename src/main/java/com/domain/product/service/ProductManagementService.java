package com.domain.product.service;

import com.domain.product.controller.dto.response.RetrieveProductResponse;
import com.domain.product.domain.Products;
import com.domain.user.domain.Account;
import com.domain.user.service.AccountService;
import com.domain.user.service.AuthService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductManagementService {

    private final ProductService productService;
    private final AuthService authService;
    private final AccountService accountService;

    public List<RetrieveProductResponse> retrieve (String keyword) {
        return productService.retrieve(keyword);
    }
    public List<RetrieveProductResponse> getMyProducts () {
        final String loginId = authService.getLoginId();
        Account account = accountService.getByLoginId(loginId);
        final Long userId = account.getUser().getId();

        return productService.findAllByUserId(userId).stream()
            .map(product -> RetrieveProductResponse.from(product))
            .collect(Collectors.toList());
    }

    public RetrieveProductResponse getById(Long productId) {
        Products product = productService.getByProductId(productId);

        return RetrieveProductResponse.from(product);
    }
}
