package com.domain.user.domain;

import com.domain.common.entity.RootEntity;
import com.domain.policy.domain.Policy;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 사용자 약관 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_policy")
public class UserPolicy extends RootEntity {

    @Comment("사용자")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Comment("약관")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "policy_id", referencedColumnName = "id")
    private Policy policy;

    private UserPolicy(User user, Policy policy) {
        this.user = user;
        this.policy = policy;
    }

    public static UserPolicy of(User user, Policy policy) {
        return new UserPolicy(user, policy);
    }

}
