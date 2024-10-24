package com.domain.user.infrastructure;

import com.domain.common.enums.Authority;
import com.domain.user.domain.QUserProject;
import com.domain.user.domain.UserProject;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserProjectQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QUserProject qUserProject = QUserProject.userProject;

    public List<UserProject> findInvolvedUsers(Long projectId) {
        return queryFactory
            .select(qUserProject)
            .from(qUserProject)
            .where(qUserProject.project.id.eq(projectId))
            .fetch();
    }

    public UserProject findOwner(Long projectId) {
        return queryFactory
            .select(qUserProject)
            .from(qUserProject)
            .where(qUserProject.project.id.eq(projectId), qUserProject.authority.eq(Authority.OWNER))
            .fetch()
            .stream().findFirst()
            .orElse(null);
    }
}
