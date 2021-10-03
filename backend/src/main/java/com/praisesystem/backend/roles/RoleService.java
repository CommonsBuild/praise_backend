package com.praisesystem.backend.roles;

import com.praisesystem.backend.roles.enums.RoleCode;
import com.praisesystem.backend.roles.model.RoleEntity;

import java.util.List;

public interface RoleService {
    List<RoleEntity> findAll();
    RoleEntity findByCode(RoleCode code);
    RoleEntity createByRoleCode(RoleCode code);
}
