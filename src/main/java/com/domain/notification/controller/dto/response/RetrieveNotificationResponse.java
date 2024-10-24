package com.domain.notification.controller.dto.response;

import com.domain.notification.domain.Notification;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class RetrieveNotificationResponse {

    /**
     * 알림ID
     */
    private final Long notifyId;

    /**
     * 알림 내용
     */
    private final String message;

    /**
     * 알림 생성일
     */
    private final LocalDateTime date;

    private RetrieveNotificationResponse(Notification notification) {
        this.notifyId = notification.getId();
        this.message = notification.getContent();
        this.date = notification.getCreateDt();
    }

    public static RetrieveNotificationResponse from(Notification notification) {
        return new RetrieveNotificationResponse(notification);
    }

}
