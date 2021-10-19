package com.praisesystem.backend.periods.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.praise.Praise;
import com.praisesystem.backend.praise.QuantifiedPraise;
import com.praisesystem.backend.users.model.UserEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "periods")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PeriodEntity extends BaseEntity {

//    @OneToOne
//    @JoinColumn(name = "pool_id", referencedColumnName = "id")
//    QuantPoolEntity pool;

    @Column(name = "name", unique = true, length = 64)
    String name;

    @Column(name = "end_date", columnDefinition = "timestamp", nullable = false)
    LocalDate endDate;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "quantifiers",
            joinColumns = {@JoinColumn(name = "period_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    Set<UserEntity> quantifiers = new HashSet<>();

    @OneToMany(mappedBy = "period", fetch = FetchType.LAZY)
    List<Praise> praises;

    @OneToMany(mappedBy = "period", fetch = FetchType.LAZY)
    List<QuantifiedPraise> quantifiedPraises;
}
