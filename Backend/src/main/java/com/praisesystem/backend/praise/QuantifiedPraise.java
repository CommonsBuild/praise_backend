package com.praisesystem.backend.praise;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.periods.model.PeriodEntity;
import com.praisesystem.backend.users.model.UserEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class QuantifiedPraise extends BaseEntity {

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false)
    PeriodEntity period;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "praise_id", nullable = false)
    Praise praise;

    @OneToOne
    @JoinColumn(name = "quantifier_id", nullable = false)
    UserEntity quantifier;

    @Column(name = "score", nullable = false)
    Long score;
}
