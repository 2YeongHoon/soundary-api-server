package com.domain.notification.controller;

import com.domain.common.dto.SuccessResponse;
import com.domain.common.utils.SuccessResponseUtils;
import com.domain.notification.controller.dto.response.RetrieveNotificationResponse;
import com.domain.notification.service.NotificationManagementService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/notify")
public class NotificationController {

    private final NotificationManagementService managementService;

    @GetMapping
    public ResponseEntity<SuccessResponse<List<RetrieveNotificationResponse>>> retrieve() {
        return SuccessResponseUtils.successResponse(managementService.findAll());
    }

    @DeleteMapping(value = "{notify-id}")
    public ResponseEntity<SuccessResponse> delete(@PathVariable("notify-id") Long notifyId) {
        managementService.delete(notifyId);
        return SuccessResponseUtils.successResponse();
    }

    @PutMapping(value = "{notify-id}/read")
    public ResponseEntity<SuccessResponse> read(@PathVariable("notify-id") Long notifyId) {
        managementService.read(notifyId);
        return SuccessResponseUtils.successResponse();
    }

    @PutMapping(value = "/read-all")
    public ResponseEntity<SuccessResponse> readAll() {
        managementService.readAll();
        return SuccessResponseUtils.successResponse();
    }

}
