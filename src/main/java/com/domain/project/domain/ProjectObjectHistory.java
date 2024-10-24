package com.domain.project.domain;

import com.domain.common.entity.ChildEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

/**
 * 프로젝트 객체 히스토리 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "project_object_history")
public class ProjectObjectHistory extends ChildEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @Comment("json data")
    @Column(name = "data", nullable = false, columnDefinition = "JSON")
    private String data;

    private ProjectObjectHistory(Project project, String data) {
        this.project = project;
        this.data = data;
    }

    public static ProjectObjectHistory of(Project project, String data) {
        return new ProjectObjectHistory(project, data);
    }

    public void update(String data) {
        this.data = data;
    }
}
