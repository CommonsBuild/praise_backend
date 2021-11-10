package com.praisesystem.backend.praise.model;

import com.praisesystem.backend.accounts.model.Account;
import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.periods.model.Period;
import com.praisesystem.backend.quantification.model.QuantifiedPraise;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "praises")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Praise extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Period period;

    @OneToMany(mappedBy = "praise", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<QuantifiedPraise> quantifiedPraises = new HashSet<>();

    @Column(name = "reason", columnDefinition = "text", nullable = false)
    String reason;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "giver_id")
    Account giver;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "recipient_id")
    Account recipient;
}
