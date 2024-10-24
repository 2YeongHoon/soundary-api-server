package com.domain.project.domain;

import com.domain.common.entity.ChildEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

/**
 * 프로젝트 액션 히스토리 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "project_action_history")
public class ProjectActionHistory extends ChildEntity {

    @Comment("프로젝트")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @Comment("json data")
    @Column(name = "data", nullable = false, columnDefinition = "JSON")
    private String data;

    private ProjectActionHistory(Project project, String data) {
        this.project = project;
        this.data = data;
    }

    public static ProjectActionHistory of(Project project, String data) {
        return new ProjectActionHistory(project, data);
    }
}
