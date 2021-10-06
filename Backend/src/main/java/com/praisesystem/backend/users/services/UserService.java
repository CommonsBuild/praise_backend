package com.praisesystem.backend.users.services;

import com.praisesystem.backend.users.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(Long id);

    UserDto findByEthereumAddress(String ethereumAddress);

    void updateNonceByEthereumAddress(String ethereumAddress);

    void createAdmin();
}
