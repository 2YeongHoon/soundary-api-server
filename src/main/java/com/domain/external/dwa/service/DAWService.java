package com.domain.external.dwa.service;

import com.domain.external.dwa.controller.dto.request.SendNotificationRequest;
import com.domain.external.dwa.controller.dto.request.SendSocketNotificationRequest;
import com.domain.notification.service.NotificationManagementService;
import com.domain.notification.service.dto.CreateNotificationDto;
import com.domain.project.domain.Project;
import com.domain.project.service.ProjectService;
import com.domain.user.domain.Account;
import com.domain.user.service.AuthService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DAWService {

    private final NotificationManagementService notificationManagementService;
    private final AuthService authService;
    private final ProjectService projectService;

    public void send(SendNotificationRequest request) {
        Project project = projectService.getByProjectId(request.getProjectId());
        final String userName = authService.getAccount().getUser().getAlias();
        final String projectName = project.getName();

        List<CreateNotificationDto> dtos = project.getInvolvedUsers().stream()
            .flatMap(involvedUser -> request.getType().stream()
                .map(type -> CreateNotificationDto.of(involvedUser, type, userName, projectName)))
            .collect(Collectors.toList());

        notificationManagementService.sendAll(dtos);
    }

    public void sendFromSocket(SendSocketNotificationRequest request) {
        Project project = projectService.getByProjectId(request.getProjectId());
        Account account = authService.getAccountByLoginId(request.getAssembleId());
        final String userName = account.getUser().getAlias();
        final String projectName = project.getName();

        List<CreateNotificationDto> dtos = project.getInvolvedUsers().stream()
            .flatMap(involvedUser -> request.getType().stream()
                .map(type -> CreateNotificationDto.of(involvedUser, type, userName, projectName)))
            .collect(Collectors.toList());

        notificationManagementService.sendAll(dtos);
    }
}
