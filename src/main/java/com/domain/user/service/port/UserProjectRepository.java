package com.domain.user.service.port;

import com.domain.user.domain.UserProject;
import java.util.List;

public interface UserProjectRepository {

    List<UserProject> findInvolvedUsers(Long projectId);
    List<UserProject> findAllByIds(List<Long> ids);
    UserProject findOwner(Long projectId);
    void removeUsers(List<Long> userProjectIds);
}
