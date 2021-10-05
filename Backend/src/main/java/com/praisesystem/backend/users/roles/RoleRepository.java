package com.praisesystem.backend.users.roles;

import com.praisesystem.backend.users.roles.enums.RoleCode;
import com.praisesystem.backend.users.roles.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByCode(RoleCode code);
}
