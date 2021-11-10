package com.praisesystem.backend.periods.model;

import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.praise.model.Praise;
import com.praisesystem.backend.quantification.model.QuantifiedPraise;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "periods")
@Entity
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Period extends BaseEntity {

    @Column(name = "name", unique = true, length = 64, nullable = false)
    String name;

    @Column(name = "end_date", columnDefinition = "timestamp", nullable = false)
    LocalDateTime endDate;

//    @JsonBackReference
    @OneToMany(mappedBy = "period",fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Praise> praises = new HashSet<>();

//    @JsonBackReference
    @OneToMany(mappedBy = "period", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<QuantifiedPraise> quantifiedPraises = new HashSet<>();
}
