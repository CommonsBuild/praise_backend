package com.praisesystem.backend.users.services;

import com.praisesystem.backend.users.dto.UserDto;
import com.praisesystem.backend.users.model.UserEntity;

import java.util.List;

public interface UserTransactionalService {
    void createAdmins();

    UserEntity register(String user);

    List<UserDto> findAll();

    UserDto findById(Long id);

    UserDto findByPublicKey(String publicKey);

    void updateNonceByPublicKey(String publicKey);
}
