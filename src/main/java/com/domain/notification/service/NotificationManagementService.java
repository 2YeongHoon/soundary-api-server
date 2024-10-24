package com.domain.notification.service;

import com.domain.notification.controller.dto.response.RetrieveNotificationResponse;
import com.domain.notification.domain.Notification;
import com.domain.notification.service.dto.CreateNotificationDto;
import com.domain.user.domain.Account;
import com.domain.user.service.AuthService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 알림 서비스
 */
@Service
@RequiredArgsConstructor
public class NotificationManagementService {

    private final NotificationService notificationService;
    private final AuthService authService;

    public List<RetrieveNotificationResponse> findAll() {
        Account account = authService.getAccount();
        final Long userId = account.getUser().getId();

        return notificationService.findAllByUserId(userId).stream()
            .map(RetrieveNotificationResponse::from)
            .collect(Collectors.toList());
    }

    public void delete(Long notifyId) {
        notificationService.delete(notifyId);
    }

    public void send(CreateNotificationDto dto) {
        notificationService.save(dto.toEntity());
    }

    public void sendAll(List<CreateNotificationDto> dtos) {
        List<Notification> notifications =
            dtos.stream().map(CreateNotificationDto::toEntity)
                .collect(Collectors.toList());

        notificationService.saveAll(notifications);
    }

    @Transactional
    public void read(Long notifyId) {
        notificationService.findById(notifyId).ifPresent(Notification::toRead);
    }

    @Transactional
    public void readAll() {
        Account account = authService.getAccount();
        final Long userId = account.getUser().getId();

        notificationService.findAllByUserId(userId).stream()
            .forEach(Notification::toRead);
    }

}
