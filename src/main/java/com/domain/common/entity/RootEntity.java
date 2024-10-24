package com.domain.common.entity;

import com.domain.common.enums.YesOrNo;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 최상위 루트 엔터티
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class RootEntity extends DomainEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @CreatedBy
    @Column(name = "create_id", nullable = false, updatable = false)
    protected String createId;

    @CreatedDate
    @Column(name = "create_dt", nullable = false, updatable = false)
    protected LocalDateTime createDt;

    @LastModifiedBy
    @Column(name = "update_id", nullable = false)
    protected String updateId;

    @LastModifiedDate
    @Column(name = "update_dt", nullable = false)
    protected LocalDateTime updateDt;

    @Column(name = "use_yn", nullable = false, columnDefinition = "char(1) default 'Y'")
    protected YesOrNo useYn = YesOrNo.Yes;

    protected void delete() {
        this.useYn = YesOrNo.No;
    }

    public LocalDateTime getCreateDt() {
        return null == createDt ? createDt : createDt.atZone(ZoneId.systemDefault())
            .withZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }

    public LocalDateTime getUpdateDt() {
        return null == updateDt ? updateDt : updateDt.atZone(ZoneId.systemDefault())
            .withZoneSameInstant(ZoneId.of("Asia/Seoul")).toLocalDateTime();
    }
}
