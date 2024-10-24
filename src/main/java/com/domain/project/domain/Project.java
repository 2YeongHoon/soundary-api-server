package com.domain.project.domain;

import com.domain.common.entity.ChildEntity;
import com.domain.common.enums.Authority;
import com.domain.common.enums.YesOrNo;
import com.domain.user.domain.Account;
import com.domain.user.domain.User;
import com.domain.user.domain.UserProject;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

/**
 * 프로젝트 엔티티
 */
@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "projects")
public class Project extends ChildEntity {

    @Comment("사용자 프로젝트")
    @OneToMany(mappedBy = "project",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Set<UserProject> userProjects = new LinkedHashSet<>();

    @Comment("프로젝트 액션 히스토리")
    @OneToMany(mappedBy = "project",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Set<ProjectActionHistory> projectActionHistorys = new LinkedHashSet<>();

    @Comment("프로젝트 객체 히스토리")
    @OneToOne(mappedBy = "project",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private ProjectObjectHistory projectObjectHistory;

    @Comment("프로젝트 소켓정보")
    @OneToOne(mappedBy = "project",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private ProjectSocket projectSocket;

    @Comment("공개여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "public_yn", nullable = false)
    private YesOrNo publicYn;

    @Comment("프로젝트명")
    @Column(name = "name", nullable = false)
    private String name;

    @Comment("설명")
    @Column(name = "description", nullable = false)
    private String description;

    @Comment("장르")
    @Column(name = "genre", nullable = false)
    private String genre;

    @Comment("커버이미지 url")
    @Column(name = "image_url")
    private String imageUrl;

    @Comment("프로젝트 최종 json")
    @Column(name = "data", nullable = false, columnDefinition = "JSON")
    private String data;

    @Builder
    private Project(YesOrNo publicYn, String name, String description
        , String genre, String imageUrl, String data) {
        this.publicYn = publicYn;
        this.name = name;
        this.description = description;
        this.genre = genre;
        this.imageUrl = imageUrl;
        this.data = data;
    }

    public void addUserProject(UserProject userProject) {
        this.userProjects.add(userProject);
    }

    public void setProjectSocket(ProjectSocket projectSocket) {
        this.projectSocket = projectSocket;
    }

    public void setProjectObjectHistory(ProjectObjectHistory projectObjectHistory) {
        this.projectObjectHistory = projectObjectHistory;
    }


    public Optional<User> getOwnUser() {
        return this.getUserProjects().stream()
            .filter(userProject -> Authority.OWNER.equals(userProject.getAuthority()))
            .map(UserProject::getUser)
            .findFirst();
    }

    public List<User> getInvolvedUsers() {
        return this.getUserProjects().stream()
            .map(UserProject::getUser)
            .collect(Collectors.toList());
    }

    public Authority getAuthority(Account account) {
        return account.getUser().getUserProjects().stream()
            .filter(userProject -> userProject.getProject().getId().equals(this.getId()))
            .findFirst()
            .map(UserProject::getAuthority)  // Optional<UserProject>에서 Optional<Authority>로 매핑
            .orElse(Authority.NONE);
    }

}
