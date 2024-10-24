package com.domain.user.domain;

import com.domain.common.entity.ChildEntity;
import com.domain.user.controller.dto.request.UpdateRequest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 사용자 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class User extends ChildEntity {

    @Comment("계정")
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Set<Account> accounts = new LinkedHashSet<>();

    @Comment("사용자 프로젝트")
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Set<UserProject> userProjects = new LinkedHashSet<>();

    @Comment("사용자 약관")
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Set<UserPolicy> userPolicies = new LinkedHashSet<>();

    @Comment("사용자 보유 상품")
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Set<UserProduct> userProducts = new LinkedHashSet<>();

    @Comment("별칭(닉네임)")
    @Column(name = "alias")
    private String alias;

    @Comment("직업")
    @Column(name = "job")
    private String job;

    @Comment("나이")
    @Column(name = "age")
    private String age;

    @Comment("선호장르")
    @Column(name = "genre")
    private String genre;

    @Comment("자기소개")
    @Column(name = "introduce", columnDefinition = "VARCHAR(300)")
    private String introduce;

    @Comment("프로필 이미지 url")
    @Column(name = "profile_image_url")
    private String profileImageUrl;

    @Builder
    private User(String alias, String job, String age, String genre, String introduce,
        String profileImageUrl) {
        this.alias = alias;
        this.job = job;
        this.age = age;
        this.genre = genre;
        this.introduce = introduce;
        this.profileImageUrl = profileImageUrl;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    public Optional<Account> getLinkedAccount() {
        return this.getAccounts().stream()
            .findFirst();
    }

    public void update(UpdateRequest request) {
        this.alias = request.getAlias();
        this.job = request.getJob();
        this.age = request.getAge();
        this.genre = request.getGenre();
        this.introduce = request.getIntroduce();
        this.profileImageUrl = request.getImageUrl();
    }

}
