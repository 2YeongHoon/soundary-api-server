package com.domain.project.infrastructure;

import com.domain.common.enums.YesOrNo;
import com.domain.project.domain.Project;
import com.domain.project.domain.QProject;
import com.domain.user.domain.QUserProject;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProjectQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QProject qProject = QProject.project;
    private final QUserProject qUserProject = QUserProject.userProject;

    public List<Project> findAllByUserId(Long userId, String keyword) {
        return queryFactory
            .select(qProject)
            .from(qProject)
            .join(qProject.userProjects, qUserProject).fetchJoin()
            .where(qUserProject.user.id.eq(userId), qProject.name.toLowerCase().contains(keyword.toLowerCase()))
            .fetch();
    }

    public List<Project> findPublicProjects(String keyword) {
        return queryFactory
            .select(qProject)
            .from(qProject)
            .where(qProject.publicYn.eq(YesOrNo.Yes), qProject.name.toLowerCase().contains(keyword.toLowerCase()))
            .fetch();
    }

    public String findJsonData(Long projectId) {
        return queryFactory
            .select(qProject.data)
            .from(qProject)
            .where(qProject.id.eq(projectId))
            .fetch()
            .stream().findFirst()
            .orElse(null);
    }
}
