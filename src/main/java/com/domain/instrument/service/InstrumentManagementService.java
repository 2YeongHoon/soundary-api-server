package com.domain.instrument.service;

import com.domain.instrument.controller.dto.response.RetrieveInstrumentResponse;
import com.domain.product.enums.ProductType;
import com.domain.user.domain.UserProduct;
import com.domain.user.service.UserProjectService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InstrumentManagementService {

    private final UserProjectService userProjectService;
    private final InstrumentService instrumentService;

    public List<RetrieveInstrumentResponse> retrieve(Long projectId) {
        // 프로젝트 소유자의 악기상품 조회
        List<UserProduct> userProducts = userProjectService.getOwner(projectId).getUser().getUserProducts()
            .stream().filter(x -> x.getProducts().getProductType().equals(
                ProductType.INSTUMENT)).collect(Collectors.toList());

        List<Long> productIds = userProducts.stream().map(x -> x.getProducts().getId()).collect(Collectors.toList());
        List<RetrieveInstrumentResponse> result = instrumentService.retrieve(productIds);
        return result;
    }
}
