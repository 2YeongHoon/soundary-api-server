package com.domain.project.service.port;

import com.domain.project.domain.Project;
import com.domain.project.domain.ProjectSocket;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository {

    List<Project> findPublicProjects(String keyword);
    String findJsonData(Long projectId);
    Optional<Project> findByProjectId(Long projectId);
    List<Project> findAllByUserId(Long userId, String keyword);
    ProjectSocket findSocket(Long projectId);

    void save(Project project);

    void delete(Long projectId);
    void deleteSocket(Long socketId);
}
