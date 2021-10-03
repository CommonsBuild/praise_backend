package com.praisesystem.backend.users.services;

import com.praisesystem.backend.users.dto.UserDto;
import com.praisesystem.backend.users.model.UserEntity;

import java.util.List;

public interface UserTransactionalService {
    UserEntity createAdmin();

    UserEntity register(String user);

    List<UserDto> findAll();

    UserEntity findById(Long id);

    UserEntity findByPublicKey(String publicKey);

    String updateNonceByPublicKey(String publicKey);
}
