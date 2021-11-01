package com.praisesystem.backend.quantification.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.praise.model.Praise;
import com.praisesystem.backend.users.model.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuantifiedPraise extends BaseEntity {

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "period_id", nullable = false)
    Period period;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "praise_id", nullable = false)
    Praise praise;

    @ManyToOne
    @JoinColumn(name = "quantifier_id", nullable = false)
    UserEntity quantifier;

    @Column(name = "score", nullable = false)
    Long score;
}
