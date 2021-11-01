package com.praisesystem.backend.users.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.periods.model.Period;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Score extends BaseEntity {

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    UserEntity user;

    @OneToOne
    @JoinColumn(name = "period_id", referencedColumnName = "id")
    Period period;

    @Column(name = "score")
    Long score;
}
