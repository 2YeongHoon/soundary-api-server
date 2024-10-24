package com.domain.instrument.domain;

import com.domain.common.entity.ChildEntity;
import com.domain.product.domain.Products;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "instrument")
public class Instrument extends ChildEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Products products;

    @Comment("악기 오디오")
    @OneToMany(mappedBy = "instrument",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private Set<Audio> audio = new LinkedHashSet<>();

    @Comment("악기이름")
    @Column(name = "instrument_name", nullable = false)
    private String instrumentName;
}
