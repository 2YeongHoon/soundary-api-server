package com.domain.user.domain;

import com.domain.common.entity.RootEntity;
import com.domain.common.enums.Authority;
import com.domain.project.domain.Project;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 계정 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_project")
public class UserProject extends RootEntity {

    @Comment("사용자")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Comment("프로젝트")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", referencedColumnName = "id")
    private Project project;

    @Comment("프로젝트 권한")
    @Enumerated(EnumType.STRING)
    @Column(name = "authority", nullable = false)
    private Authority authority = Authority.VIEWER;

    private UserProject(User user, Project project, Authority authority) {
        this.user = user;
        this.project = project;
        this.authority = authority;
    }

    public void updateAuthority(Authority authority) {
        this.authority = authority;
    }

    public static UserProject of(User user, Project project, Authority authority) {
        return new UserProject(user, project, authority);
    }

}
