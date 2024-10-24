package com.domain.notification.service.dto;

import com.domain.notification.domain.Notification;
import com.domain.notification.enums.NotificationType;
import com.domain.user.domain.User;
import lombok.Getter;

@Getter
public class CreateNotificationDto {

    /**
     * 유저
     */
    private final User receiver;

    /**
     * 알림 타입
     */
    private final NotificationType type;

    /**
     * 알림 내용
     */
    private final String content;

    private CreateNotificationDto(User receiver, NotificationType type, String name, String projectName) {
        this.receiver = receiver;
        this.type = type;
        this.content = makeContent(type, name, projectName);
    }

    public static CreateNotificationDto of(User receiver, NotificationType type, String name,
        String projectName) {
        return new CreateNotificationDto(receiver, type, name, projectName);
    }

    public Notification toEntity() {
        return Notification.of(receiver, type, content);
    }

    private String makeContent(NotificationType type, String name, String projectName) {
        return String.format(type.message(), name, projectName);
    }

}
