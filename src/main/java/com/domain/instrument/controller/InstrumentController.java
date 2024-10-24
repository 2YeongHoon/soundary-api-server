package com.domain.instrument.controller;

import com.domain.common.dto.SuccessResponse;
import com.domain.common.utils.SuccessResponseUtils;
import com.domain.instrument.controller.dto.response.RetrieveInstrumentResponse;
import com.domain.instrument.service.InstrumentManagementService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/instrument")
@RestController
@RequiredArgsConstructor
public class InstrumentController {

    private final InstrumentManagementService instrumentManagementService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<RetrieveInstrumentResponse>>> retrieve(@RequestParam Long projectId) {
        return SuccessResponseUtils.successResponse(instrumentManagementService.retrieve(projectId));
    }
}
