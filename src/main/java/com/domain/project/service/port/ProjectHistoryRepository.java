package com.domain.project.service.port;

import com.domain.project.domain.ProjectActionHistory;
import com.domain.project.domain.ProjectObjectHistory;

public interface ProjectHistoryRepository {
    ProjectObjectHistory getObjectHistory(Long projectId);
    void saveObjectHistory(ProjectObjectHistory request);
    void saveActionHistory(ProjectActionHistory request);
}
