package com.praisesystem.backend.users.services;

import com.praisesystem.backend.users.dto.UserDto;
import com.praisesystem.backend.users.model.UserEntity;

import java.util.List;
import java.util.Set;

public interface UserService {
    List<UserDto> findAll();

    Long countUsers();

    Set<UserEntity> findRandomUsers(Long requiredCount);

    UserDto findById(Long id);

    UserDto findByEthereumAddress(String ethereumAddress);

    void updateNonceByEthereumAddress(String ethereumAddress);

    void createAdmin();
}
