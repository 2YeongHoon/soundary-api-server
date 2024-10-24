package com.domain.external.dwa.controller;

import com.domain.common.dto.SuccessResponse;
import com.domain.common.utils.SuccessResponseUtils;
import com.domain.external.dwa.controller.dto.request.SendNotificationRequest;
import com.domain.external.dwa.controller.dto.request.SendSocketNotificationRequest;
import com.domain.external.dwa.service.DAWService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/daw")
public class DAWController {

    private final DAWService service;

    @PostMapping("/notify")
    public ResponseEntity<SuccessResponse> send(@RequestBody @Valid SendNotificationRequest request) {
        service.send(request);
        return SuccessResponseUtils.successResponse();
    }

    @PreAuthorize("@projectSecurity.fromSocket(#httpServletRequest.getHeader('fromSocket'))")
    @PostMapping("/notify-socket")
    public ResponseEntity<SuccessResponse> sendFromSocket(@RequestBody @Valid SendSocketNotificationRequest request,
        HttpServletRequest httpServletRequest) {
        service.sendFromSocket(request);
        return SuccessResponseUtils.successResponse();
    }
}
