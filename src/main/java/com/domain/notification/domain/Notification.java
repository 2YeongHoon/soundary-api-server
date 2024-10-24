package com.domain.notification.domain;

import com.domain.common.entity.ChildEntity;
import com.domain.common.enums.YesOrNo;
import com.domain.notification.enums.NotificationType;
import com.domain.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notifications")
public class Notification extends ChildEntity {

    @Comment("사용자")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Comment("알림 타입")
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private NotificationType type;

    @Comment("알림 내용")
    @Column(name = "content", nullable = false)
    private String content;

    @Comment("읽음 여부")
    @Enumerated(EnumType.STRING)
    @Column(name = "read_yn", nullable = false)
    private YesOrNo readYn = YesOrNo.No;

    @Builder
    private Notification(User user, NotificationType type, String content) {
        this.user = user;
        this.type = type;
        this.content = content;
        this.readYn = YesOrNo.No;
    }

    public static Notification of(User user, NotificationType type, String content){
        return Notification.builder()
            .user(user)
            .type(type)
            .content(content)
            .build();
    }

    public void toRead() {
        this.readYn = YesOrNo.Yes;
    }

}
