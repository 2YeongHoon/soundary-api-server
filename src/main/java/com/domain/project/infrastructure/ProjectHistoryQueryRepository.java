package com.domain.project.infrastructure;

import com.domain.project.domain.ProjectObjectHistory;
import com.domain.project.domain.QProjectObjectHistory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProjectHistoryQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QProjectObjectHistory qProjectObjectHistory = QProjectObjectHistory.projectObjectHistory;

    public ProjectObjectHistory findObjectHistoryByProjectId(Long projectId) {
        return queryFactory
            .select(qProjectObjectHistory)
            .from(qProjectObjectHistory)
            .where(qProjectObjectHistory.project.id.eq(projectId))
            .fetch()
            .stream().findFirst()
            .orElse(null);
    }
}
