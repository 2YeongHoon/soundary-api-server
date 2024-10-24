package com.domain.user.service;

import com.domain.project.controller.dto.response.InvolvedUserResponse;
import com.domain.user.domain.UserProject;
import com.domain.user.service.port.UserProjectRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 유저 프로젝트 서비스
 */
@Service
@RequiredArgsConstructor
public class UserProjectService {

    private final UserProjectRepository userProjectRepository;

    @Transactional(readOnly = true)
    public List<InvolvedUserResponse> getInvolvedUsers(Long projectId) {
        return userProjectRepository.findInvolvedUsers(projectId).stream()
            .map(InvolvedUserResponse::of)
            .collect(Collectors.toList());
    }

    public List<UserProject> getByIds(List<Long> ids) {
        return userProjectRepository.findAllByIds(ids);
    }

    public UserProject getOwner(Long projectId) {
        return userProjectRepository.findOwner(projectId);
    }

    public void removeUsers(List<Long> userProjectIds) {
        userProjectRepository.removeUsers(userProjectIds);
    }
}
