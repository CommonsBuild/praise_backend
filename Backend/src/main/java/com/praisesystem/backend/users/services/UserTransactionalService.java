package com.praisesystem.backend.users.services;

import com.praisesystem.backend.users.dto.request.UserFilter;
import com.praisesystem.backend.users.dto.response.UserDto;
import com.praisesystem.backend.users.model.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface UserTransactionalService {
    void createAdmins();

    UserEntity register(String user);

    Page<UserDto> findAll(UserFilter filter, Pageable pageable);

    UserDto findById(Long id);

    UserDto findByEthereumAddress(String ethereumAddress);

    void updateNonceByEthereumAddress(String ethereumAddress);

    Long countUsers();

    Set<UserEntity> findRandomUsers(Long requiredCount);

    UserEntity findUserEntityById(Long id);

    Page<UserDto> findByAddressOrDiscordTagOrTelegramHandle(String pattern, Pageable pageable);

    UserDto addToQuantPool(Long userId);
}
