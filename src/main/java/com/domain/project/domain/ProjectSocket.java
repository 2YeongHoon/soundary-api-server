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
import org.hibernate.annotations.Comment;

/**
 * 프로젝트 소켓 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "project_socket")
public class ProjectSocket extends ChildEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @Comment("소켓 접속 포트")
    @Column(name = "port", nullable = false)
    private Long port;

    private ProjectSocket(Project project, Long port) {
        this.project = project;
        this.port = port;
    }

    public static ProjectSocket of(Project project, Long port) {
        return new ProjectSocket(project, port);
    }

    public void update(Long port) {
        this.port = port;
    }
}
