package com.praisesystem.backend.users.impl;

import com.praisesystem.backend.common.exceptions.exceptiontypes.NotFoundObjectException;
import com.praisesystem.backend.configuration.properties.ApplicationProperties;
import com.praisesystem.backend.roles.RoleService;
import com.praisesystem.backend.roles.enums.RoleCode;
import com.praisesystem.backend.roles.model.RoleEntity;
import com.praisesystem.backend.users.dto.UserDto;
import com.praisesystem.backend.users.mapper.UserMapper;
import com.praisesystem.backend.users.UserRepository;
import com.praisesystem.backend.users.model.UserEntity;
import com.praisesystem.backend.users.services.UserTransactionalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
@AllArgsConstructor // TODO: 03.10.2021 Refactor all methods to return UserDTO 
public class UserTransactionalServiceImpl implements UserTransactionalService {

    ApplicationProperties properties;
    UserRepository userRepository;
    RoleService roleService;
    UserMapper userMapper;

    @Override
    public UserEntity createAdmin() {
        return userRepository.findByPublicKey(properties.getAdminPublicKey()).orElseGet(() -> {
            List<RoleEntity> roles = roleService.findAll();
            UserEntity newAdminUser = userMapper.toNewUserFromPublicKeyAndRoles(properties.getAdminPublicKey(), roles);
            return userRepository.save(newAdminUser);
        });

    }

    @Override
    public UserEntity register(String publicKey) {
        RoleEntity roleUser = roleService.findByCode(RoleCode.ROLE_USER);
        UserEntity newUser = userMapper.toNewUserFromPublicKeyAndRoles(publicKey, Collections.singletonList(roleUser));
        return userRepository.save(newUser);
    }

    @Override
    public UserEntity findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundObjectException("User not found"));
    }

    @Override
    public UserEntity findByPublicKey(String publicKey) {
        Optional<UserEntity> optionalUser =  userRepository.findByPublicKey(publicKey);
        return optionalUser.orElseGet(() -> register(publicKey));
    }

    @Override
    public String updateNonceByPublicKey(String publicKey) {
        UserEntity user = findByPublicKey(publicKey);

        user.updateNonce();
        return userRepository.save(user).getNonce();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toUserDto).collect(Collectors.toList());
    }
}