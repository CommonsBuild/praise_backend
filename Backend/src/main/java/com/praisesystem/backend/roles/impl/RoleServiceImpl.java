package com.praisesystem.backend.roles.impl;

import com.praisesystem.backend.roles.RoleRepository;
import com.praisesystem.backend.roles.RoleService;
import com.praisesystem.backend.roles.enums.RoleCode;
import com.praisesystem.backend.roles.model.RoleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;

    @Override
    public List<RoleEntity> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public RoleEntity findByCode(RoleCode code) {
        return roleRepository.findByCode(code);
    }

    @Override
    public RoleEntity createByRoleCode(RoleCode code) {
        return roleRepository.save(new RoleEntity(code));
    }
}
