package com.domain.project.infrastructure;

import com.domain.project.domain.ProjectActionHistory;
import com.domain.project.domain.ProjectObjectHistory;
import com.domain.project.service.port.ProjectHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectHistoryRepositoryImpl implements ProjectHistoryRepository {

    private final ProjectActionHistoryJpaRepository projectActionHistoryJpaRepository;
    private final ProjectObjectHistoryJpaRepository projectObjectHistoryJpaRepository;
    private final ProjectHistoryQueryRepository projectHistoryQueryRepository;

    @Override
    public ProjectObjectHistory getObjectHistory(Long projectId) {
        return projectHistoryQueryRepository.findObjectHistoryByProjectId(projectId);
    }

    @Override
    public void saveObjectHistory(ProjectObjectHistory request) {
        projectObjectHistoryJpaRepository.save(request);
    }

    @Override
    public void saveActionHistory(ProjectActionHistory request) {
        projectActionHistoryJpaRepository.save(request);
    }
}
