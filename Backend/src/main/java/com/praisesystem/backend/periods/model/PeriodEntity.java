package com.praisesystem.backend.periods.model;

import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.pools.QuantPoolEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@Table(name = "periods")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PeriodEntity extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "pool_id", referencedColumnName = "id")
    QuantPoolEntity pool;

    @Column(name = "name", unique = true, length = 64)
    String name;

    @Column(name = "end_date", columnDefinition = "timestamp", nullable = false)
    LocalDate endDate;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "status", nullable = false)
//    PeriodStatus status;
}
