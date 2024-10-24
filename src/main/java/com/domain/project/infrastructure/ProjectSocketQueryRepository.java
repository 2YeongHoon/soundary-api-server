package com.domain.project.infrastructure;

import com.domain.project.domain.ProjectSocket;
import com.domain.project.domain.QProjectSocket;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProjectSocketQueryRepository {

    private final JPAQueryFactory queryFactory;
    private final QProjectSocket qProjectSocket = QProjectSocket.projectSocket;

    public ProjectSocket findSocketInfo(Long projectId) {
        return queryFactory
            .select(qProjectSocket)
            .from(qProjectSocket)
            .where(qProjectSocket.project.id.eq(projectId))
            .fetch()
            .stream().findFirst()
            .orElse(null);
    }

    public Long deleteByProjectId(Long projectId) {
        return queryFactory
            .delete(qProjectSocket)
            .where(qProjectSocket.project.id.eq(projectId))
            .execute();
    }
}
