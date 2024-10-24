package com.domain.common.entity;

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
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * 최상위 자식 엔터티
 */
@Getter
@MappedSuperclass
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class ChildEntity extends DomainEntity implements Serializable {

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

    protected ChildEntity(Long id) {
        this.id = id;
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
