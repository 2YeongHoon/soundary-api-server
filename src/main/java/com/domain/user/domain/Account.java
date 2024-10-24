package com.domain.user.domain;

import com.domain.common.entity.RootEntity;
import com.domain.user.enums.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
@Table(name = "accounts", uniqueConstraints = {
    @UniqueConstraint(name = "u_login_id", columnNames = {"login_id"})})
public class Account extends RootEntity {

    @Comment("사용자")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Comment("로그인 아이디")
    @Column(name = "login_id", nullable = false)
    private String loginId;

    @Comment("패스워드")
    @Column(name = "password", nullable = false)
    private String password;

    @Comment("가입유형")
    @Enumerated(EnumType.STRING)
    @Column(name = "account_type", nullable = false)
    private AccountType accountType;

    private Account(User user, String email, String password, AccountType type) {
        this.user = user;
        this.loginId = email;
        this.password = password;
        this.accountType = type;
    }

    public static Account of(User user, String email, String password, AccountType type) {
        return new Account(user, email, password, type);
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

}
