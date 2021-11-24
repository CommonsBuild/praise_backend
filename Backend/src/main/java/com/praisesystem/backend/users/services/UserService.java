package com.praisesystem.backend.users.services;

import com.praisesystem.backend.users.dto.request.UserLimitedFilter;
import com.praisesystem.backend.users.dto.response.UserLimitedDto;
import com.praisesystem.backend.users.dto.request.UserFilter;
import com.praisesystem.backend.users.dto.response.UserDto;
import com.praisesystem.backend.users.model.UserEntity;
import com.praisesystem.backend.users.roles.enums.RoleCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface UserService {
    Page<UserDto> findAll(UserFilter filter, Pageable pageable);

    Page<UserLimitedDto> findAllLimited(UserLimitedFilter filter, Pageable pageable);

    Long countUsers();

    Set<UserEntity> findRandomUsers(Long requiredCount);

    UserDto findUserInfoById(Long id);

    UserDto findByEthereumAddress(String ethereumAddress);

    void updateNonceByEthereumAddress(String ethereumAddress);

    void createAdmin();

    Page<UserDto> findByAddressOrDiscordTagOrTelegramHandle(String pattern, Pageable pageable);

    UserDto addRole(Long userId, RoleCode code);

    UserDto removeRole(Long userId, RoleCode code);

    UserLimitedDto findLimitedUserInfoById(Long userId);
}
