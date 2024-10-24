package com.domain.project.service;

import com.domain.project.domain.ProjectActionHistory;
import com.domain.project.domain.ProjectObjectHistory;
import com.domain.project.service.port.ProjectHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 프로젝트 히스토리 서비스
 */
@Service
@RequiredArgsConstructor
public class ProjectHistoryService {

    private final ProjectHistoryRepository projectHistoryRepository;

    public void saveActionHistory (ProjectActionHistory history) {
        projectHistoryRepository.saveActionHistory(history);
    }
    public void saveObjectHistory (ProjectObjectHistory history) {
        projectHistoryRepository.saveObjectHistory(history);
    }

    public ProjectObjectHistory getObjectHistory(Long projectId) {
        return projectHistoryRepository.getObjectHistory(projectId);
    }

}
