package com.domain.project.service;

import com.domain.common.enums.Errors;
import com.domain.common.exception.BaseNotFoundException;
import com.domain.project.domain.Project;
import com.domain.project.domain.ProjectSocket;
import com.domain.project.service.port.ProjectRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 프로젝트 서비스
 */
@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public List<Project> getPublicProjects(String keywords) {
        return projectRepository.findPublicProjects(keywords);
    }

    @Transactional(readOnly = true)
    public String getProjectObject(Long project) {
        return projectRepository.findJsonData(project);
    }

    @Transactional(readOnly = true)
    public Project getByProjectId(Long id) {
        return projectRepository.findByProjectId(id)
            .orElseThrow(() -> new BaseNotFoundException(Errors.NOT_FOUND_PROJECT));
    }

    @Transactional(readOnly = true)
    public String getJsonData(Long projectId) {
        return projectRepository.findJsonData(projectId);
    }

    @Transactional(readOnly = true)
    public List<Project> findAllByUserId(Long userId, String keywords) {
        return projectRepository.findAllByUserId(userId, keywords);
    }

    @Transactional
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Transactional
    public void delete(Long projectId) {
        projectRepository.delete(projectId);
    }

    @Transactional(readOnly = true)
    public ProjectSocket getSocket(Long projectId) {
        return projectRepository.findSocket(projectId);
    }

    @Transactional
    public void deleteSocket(Long socketId) {
        projectRepository.deleteSocket(socketId);
    }
}
