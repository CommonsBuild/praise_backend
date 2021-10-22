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
import java.util.Set;

@Slf4j(topic = "[USER SERVICE]")
@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserTransactionalService userTransactionalService;

    @Override
    public List<UserDto> findAll() {
        return userTransactionalService.findAll();
    }

    @Override
    public Long countUsers() {
        return userTransactionalService.countUsers();
    }

    @Override
    public Set<UserEntity> findRandomUsers(Long requiredCount) {
        return userTransactionalService.findRandomUsers(requiredCount);
    }

    @Override
    public UserDto findById(Long id) {
        return userTransactionalService.findById(id);
    }

    @Override
    public UserDto findByEthereumAddress(String ethereumAddress) {
        return userTransactionalService.findByEthereumAddress(ethereumAddress);
    }

    @Override
    public void updateNonceByEthereumAddress(String ethereumAddress) {
        userTransactionalService.updateNonceByEthereumAddress(ethereumAddress);
    }

    @Override
    public void createAdmin() {
        userTransactionalService.createAdmins();
    }
}
