package com.domain.external.dwa.controller.dto.request;

import com.domain.notification.enums.NotificationType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SendNotificationRequest {

    private Long projectId;

    private List<NotificationType> type;

}
