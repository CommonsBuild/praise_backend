package com.praisesystem.backend.users.impl;

import com.praisesystem.backend.users.dto.request.UserFilter;
import com.praisesystem.backend.users.dto.response.UserDto;
import com.praisesystem.backend.users.model.UserEntity;
import com.praisesystem.backend.users.roles.enums.RoleCode;
import com.praisesystem.backend.users.services.UserService;
import com.praisesystem.backend.users.services.UserTransactionalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j(topic = "[USER SERVICE]")
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserTransactionalService userTransactionalService;

    @Override
    public Page<UserDto> findAll(UserFilter filter, Pageable pageable) {
        return userTransactionalService.findAll(filter, pageable);
    }

    @Override
    public Long countUsers() {
        return userTransactionalService.countUsers();
    }

    @Override
    public Set<UserEntity> findRandomUsers(Long requiredCount) {
        return userTransactionalService.findRandomUsers(requiredCount);
    }

    @Override
    public UserDto findUserDtoById(Long id) {
        return userTransactionalService.findById(id);
    }

    @Override
    public UserDto findByEthereumAddress(String ethereumAddress) {
        return userTransactionalService.findByEthereumAddress(ethereumAddress);
    }

    @Override
    public void updateNonceByEthereumAddress(String ethereumAddress) {
        userTransactionalService.updateNonceByEthereumAddress(ethereumAddress);
    }

    @Override
    public void createAdmin() {
        userTransactionalService.createAdmins();
    }

    @Override
    public UserEntity findUserEntityById(Long id) {
        return userTransactionalService.findUserEntityById(id);
    }

    @Override
    public Page<UserDto> findByAddressOrDiscordTagOrTelegramHandle(String pattern, Pageable pageable) {
        return userTransactionalService.findByAddressOrDiscordTagOrTelegramHandle(pattern, pageable);
    }

    @Override
    public UserDto addRole(Long userId, RoleCode code) {
        return userTransactionalService.addRole(userId, code);
    }

    @Override
    public UserDto removeRole(Long userId, RoleCode code) {
        return userTransactionalService.removeRole(userId, code);
    }
}
