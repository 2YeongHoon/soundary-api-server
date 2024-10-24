package com.domain.policy.domain;

import com.domain.common.entity.ChildEntity;
import com.domain.common.enums.YesOrNo;
import com.domain.user.domain.UserPolicy;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

/**
 * 약관 엔티티
 */
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "policy")
public class Policy extends ChildEntity {

    @Comment("사용자 약관동의")
    @OneToMany(mappedBy = "policy",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Set<UserPolicy> userPolicies = new LinkedHashSet<>();

    @Comment("약관코드")
    @Column(name = "code", nullable = false)
    private String code;

    @Comment("약관버전")
    @Column(name = "version", nullable = false)
    private int version;

    @Comment("약관명")
    @Column(name = "name", nullable = false)
    private String name;

    @Comment("약관내용")
    @Column(name = "content", nullable = false)
    private String content;

    @Comment("필수여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "required_yn", nullable = false)
    private YesOrNo requiredYn;

    @Comment("사용여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "use_yn", nullable = false)
    private YesOrNo userYn;

    public void addUserPolicy(UserPolicy userPolicy) {
        this.userPolicies.add(userPolicy);
    }
}
