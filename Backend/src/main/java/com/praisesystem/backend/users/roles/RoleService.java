package com.praisesystem.backend.users.roles;

import com.praisesystem.backend.users.roles.enums.RoleCode;
import com.praisesystem.backend.users.roles.model.RoleEntity;

import java.util.List;

public interface RoleService {
    List<RoleEntity> findAll();
    RoleEntity findByCode(RoleCode code);
    RoleEntity createByRoleCode(RoleCode code);
}
