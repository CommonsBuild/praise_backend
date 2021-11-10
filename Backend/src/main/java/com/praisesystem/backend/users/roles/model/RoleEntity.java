package com.praisesystem.backend.users.roles.model;

import com.praisesystem.backend.common.persistence.BaseEntity;
import com.praisesystem.backend.users.model.UserEntity;
import com.praisesystem.backend.users.roles.enums.RoleCode;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "roles")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleEntity extends BaseEntity {

    public RoleEntity(RoleCode roleCode) {
        code = roleCode;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "code", unique = true, nullable = false)
    RoleCode code;

//    @JsonBackReference
    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    List<UserEntity> users = new ArrayList<>();
}
