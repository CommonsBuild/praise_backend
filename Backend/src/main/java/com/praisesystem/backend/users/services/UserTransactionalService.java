package com.praisesystem.backend.users.services;

import com.praisesystem.backend.users.dto.UserDto;
import com.praisesystem.backend.users.model.UserEntity;

import java.util.List;
import java.util.Set;

public interface UserTransactionalService {
    void createAdmins();

    UserEntity register(String user);

    List<UserDto> findAll();

    UserDto findById(Long id);

    UserDto findByEthereumAddress(String ethereumAddress);

    void updateNonceByEthereumAddress(String ethereumAddress);

    Long countUsers();

    Set<UserEntity> findRandomUsers(Long requiredCount);


}
