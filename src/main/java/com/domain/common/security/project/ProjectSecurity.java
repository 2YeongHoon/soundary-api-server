package com.domain.common.security.project;

import com.domain.common.enums.Authority;
import com.domain.user.domain.User;
import com.domain.user.service.AuthService;
import org.springframework.stereotype.Component;

@Component("projectSecurity")
public class ProjectSecurity {

    private final AuthService authService;

    public ProjectSecurity(AuthService authService) {
        this.authService = authService;
    }

    public boolean isOwner(Long projectId) {
        User user = authService.getAccount().getUser();

        boolean isOwner = user.getUserProjects().stream()
            .anyMatch(userProject ->
                userProject.getProject().getId() == projectId &&
                    userProject.getAuthority().equals(Authority.OWNER)
            );

        if (!isOwner) {
            return false;
        }

        return true;
    }

    public boolean isEditableUser(Long projectId) {
        User user = authService.getAccount().getUser();

        boolean isEditableUser = user.getUserProjects().stream()
            .anyMatch(userProject ->
                userProject.getProject().getId() == projectId &&
                    (userProject.getAuthority().equals(Authority.OWNER) || userProject.getAuthority().equals(Authority.EDITOR))
            );

        if (!isEditableUser) {
            return false;
        }

        return true;
    }

    public boolean fromSocket(Boolean fromSocket) {

        if(fromSocket == null) return false;

        return fromSocket;
    }
}
