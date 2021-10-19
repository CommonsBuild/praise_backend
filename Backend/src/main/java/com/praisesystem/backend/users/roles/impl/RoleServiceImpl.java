package com.praisesystem.backend.users.roles.impl;

import com.praisesystem.backend.users.roles.RoleRepository;
import com.praisesystem.backend.users.roles.RoleService;
import com.praisesystem.backend.users.roles.enums.RoleCode;
import com.praisesystem.backend.users.roles.model.RoleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j(topic = "[ROLE SERVICE]")
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
    public void createByRoleCode(RoleCode code) {
        roleRepository.save(new RoleEntity(code));
        log.info("Role ({}) created.", code.getLabel());
    }
}
