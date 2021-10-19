//package com.praisesystem.backend.pools;
//
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import com.praisesystem.backend.common.persistence.BaseEntity;
//import com.praisesystem.backend.periods.model.PeriodEntity;
//import com.praisesystem.backend.users.model.UserEntity;
//
//import javax.persistence.*;
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Table(name = "pools")
//public class QuantPoolEntity extends BaseEntity {
//
//    @OneToOne
//    @JoinColumn(name = "period_id", referencedColumnName = "id")
//    PeriodEntity period;
//
//    @JsonBackReference
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "quantifiers",
//            joinColumns = {@JoinColumn(name = "pool_id", referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")})
//    Set<UserEntity> quantifiers = new HashSet<>();
//}
