package com.praisesystem.backend.praise;

import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.periods.model.PeriodEntity;
import com.praisesystem.backend.users.model.UserEntity;

import javax.persistence.*;

@Entity
public class QuantifiedPraise extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false)
    PeriodEntity period;

    @ManyToOne
    @JoinColumn(name = "praise_id", nullable = false)
    Praise praise;

    @OneToOne
    @JoinColumn(name = "quantifier_id", nullable = false)
    UserEntity quantifier;

    @Column(name = "score", nullable = false)
    Long score;
}
