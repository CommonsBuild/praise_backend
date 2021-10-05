package com.praisesystem.backend.users.services;

import com.praisesystem.backend.users.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(Long id);

    UserDto findByPublicKey(String publicKey);

    void updateNonceByPublicKey(String publicKey);

    void createAdmin();
}
