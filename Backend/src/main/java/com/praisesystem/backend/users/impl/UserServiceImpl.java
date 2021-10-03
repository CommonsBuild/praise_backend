package com.praisesystem.backend.users.impl;

import com.praisesystem.backend.users.dto.UserDto;
import com.praisesystem.backend.users.model.UserEntity;
import com.praisesystem.backend.users.services.UserService;
import com.praisesystem.backend.users.services.UserTransactionalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserTransactionalService userTransactionalService;

    @Override
    public UserEntity register(String publicKey) {
        return userTransactionalService.register(publicKey);
    }

    @Override
    public List<UserDto> findAll() {
        return userTransactionalService.findAll();
    }

    @Override
    public UserEntity findById(Long id) {
        return userTransactionalService.findById(id);
    }

    @Override
    public UserEntity findByPublicKey(String publicKey) {
        return userTransactionalService.findByPublicKey(publicKey);
    }

    @Override
    public void updateNonceByPublicKey(String publicKey) {
        userTransactionalService.updateNonceByPublicKey(publicKey);
    }

    @Override
    public void createAdmin() {
        userTransactionalService.createAdmin();
    }
}
