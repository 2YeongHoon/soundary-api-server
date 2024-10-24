package com.domain.common.entity;

import jakarta.persistence.MappedSuperclass;
import java.io.Serializable;
import lombok.Getter;

/**
 * 최상위 도메인 엔터티
 */
@Getter
@MappedSuperclass
public abstract class DomainEntity implements Serializable {

}
