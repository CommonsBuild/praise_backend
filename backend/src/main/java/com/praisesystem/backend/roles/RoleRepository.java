package com.praisesystem.backend.roles;

import com.praisesystem.backend.roles.enums.RoleCode;
import com.praisesystem.backend.roles.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByCode(RoleCode code);
}
