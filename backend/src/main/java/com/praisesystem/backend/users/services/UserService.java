package com.praisesystem.backend.users.services;

import com.praisesystem.backend.users.dto.UserDto;
import com.praisesystem.backend.users.model.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity register(String user);

    List<UserDto> findAll();

    UserEntity findById(Long id);

    UserEntity findByPublicKey(String publicKey);

    void updateNonceByPublicKey(String publicKey);

    void createAdmin();
}
