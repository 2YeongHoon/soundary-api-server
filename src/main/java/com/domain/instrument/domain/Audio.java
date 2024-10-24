package com.domain.instrument.domain;

import com.domain.common.entity.ChildEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "audio")
public class Audio extends ChildEntity {

    @Comment("악기")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "instrument_id", referencedColumnName = "id")
    private Instrument instrument;

    @Comment("s3 파일명")
    @Column(name = "s3_key", nullable = false)
    private String s3Key;

    @Comment("음 코드")
    @Column(name = "code", nullable = false)
    private Long code;

    @Comment("음 설명")
    @Column(name = "description", nullable = false)
    private String description;
}
