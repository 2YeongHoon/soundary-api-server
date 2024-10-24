package com.domain.user.infrastructure;

import com.domain.project.infrastructure.UserProjectJpaRepository;
import com.domain.user.domain.UserProject;
import com.domain.user.service.port.UserProjectRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserProjectRepositoryImpl implements UserProjectRepository {

    private final UserProjectQueryRepository userProjectQueryRepository;
    private final UserProjectJpaRepository userProjectJpaRepository;

    @Override
    public List<UserProject> findInvolvedUsers(Long projectId) {
        return userProjectQueryRepository.findInvolvedUsers(projectId);
    }

    @Override
    public List<UserProject> findAllByIds(List<Long> ids) {
        return userProjectJpaRepository.findAllById(ids);
    }

    @Override
    public UserProject findOwner(Long projectId) {
        return userProjectQueryRepository.findOwner(projectId);
    }

    @Override
    public void removeUsers(List<Long> userProjectIds) {
        userProjectJpaRepository.deleteAllById(userProjectIds);
    }
}
