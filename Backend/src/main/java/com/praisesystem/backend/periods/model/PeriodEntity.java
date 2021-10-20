package com.praisesystem.backend.periods.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.praise.Praise;
import com.praisesystem.backend.praise.QuantifiedPraise;
import com.praisesystem.backend.users.model.UserEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "periods")
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PeriodEntity extends BaseEntity {

    @Column(name = "name", unique = true, length = 64, nullable = false)
    String name;

    @Column(name = "end_date", columnDefinition = "timestamp", nullable = false)
    LocalDateTime endDate;

    @JsonBackReference
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "quantifiers",
            joinColumns = {@JoinColumn(name = "period_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<UserEntity> quantifiers = new HashSet<>();

    @JsonBackReference
    @OneToMany(mappedBy = "period",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<Praise> praises = new ArrayList<>();

    @JsonBackReference
    @OneToMany(mappedBy = "period", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    List<QuantifiedPraise> quantifiedPraises = new ArrayList<>();
}
