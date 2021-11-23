package com.praisesystem.backend.users.services;

import com.praisesystem.backend.users.dto.request.UserFilter;
import com.praisesystem.backend.users.dto.response.UserDto;
import com.praisesystem.backend.users.model.UserEntity;
import com.praisesystem.backend.users.roles.enums.RoleCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface UserService {
    Page<UserDto> findAll(UserFilter filter, Pageable pageable);

    Long countUsers();

    Set<UserEntity> findRandomUsers(Long requiredCount);

    UserDto findUserDtoById(Long id);

    UserDto findByEthereumAddress(String ethereumAddress);

    void updateNonceByEthereumAddress(String ethereumAddress);

    void createAdmin();

    UserEntity findUserEntityById(Long id);

    Page<UserDto> findByAddressOrDiscordTagOrTelegramHandle(String pattern, Pageable pageable);

    UserDto addRole(Long userId, RoleCode code);

    UserDto removeRole(Long userId, RoleCode code);
}
