package com.domain.project.infrastructure;

import com.domain.project.domain.Project;
import com.domain.project.domain.ProjectSocket;
import com.domain.project.service.port.ProjectRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProjectRepositoryImpl implements ProjectRepository {

    private final ProjectJpaRepository projectJpaRepository;
    private final ProjectQueryRepository projectQueryRepository;
    private final ProjectSocketQueryRepository projectSocketQueryRepository;

    @Override
    public List<Project> findPublicProjects(String keywords) {
        return projectQueryRepository.findPublicProjects(keywords);
    }

    @Override
    public String findJsonData(Long projectId) {
        return projectQueryRepository.findJsonData(projectId);
    }

    @Override
    public Optional<Project> findByProjectId(Long projectId) {
        return projectJpaRepository.findById(projectId);
    }

    @Override
    public List<Project> findAllByUserId(Long userId, String keyword) {
        return projectQueryRepository.findAllByUserId(userId, keyword);
    }

    @Override
    public ProjectSocket findSocket(Long projectId) {
        return projectSocketQueryRepository.findSocketInfo(projectId);
    }

    @Override
    public void save(Project project) {
        projectJpaRepository.save(project);
    }

    @Override
    public void delete(Long projectId) {
        projectJpaRepository.deleteById(projectId);
    }

    @Override
    public void deleteSocket(Long projectId) {
        projectSocketQueryRepository.deleteByProjectId(projectId);
    }

}
