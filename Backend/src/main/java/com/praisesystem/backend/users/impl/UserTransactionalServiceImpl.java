package com.praisesystem.backend.users.impl;

import com.praisesystem.backend.common.exceptions.exceptiontypes.NotFoundObjectException;
import com.praisesystem.backend.common.exceptions.exceptiontypes.ValidationException;
import com.praisesystem.backend.configuration.properties.ApplicationProperties;
import com.praisesystem.backend.users.dto.request.UserFilter;
import com.praisesystem.backend.users.dto.response.UserDto;
import com.praisesystem.backend.users.mapper.UserMapper;
import com.praisesystem.backend.users.model.UserEntity;
import com.praisesystem.backend.users.repositories.UserRepository;
import com.praisesystem.backend.users.repositories.specifications.UserFilterSpecification;
import com.praisesystem.backend.users.repositories.specifications.UserSearchForAddingQuantifierSpecification;
import com.praisesystem.backend.users.roles.RoleService;
import com.praisesystem.backend.users.roles.enums.RoleCode;
import com.praisesystem.backend.users.roles.model.RoleEntity;
import com.praisesystem.backend.users.services.UserTransactionalService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Slf4j(topic = "[USER TRANSACTIONAL SERVICE]")
@Service
@Transactional
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserTransactionalServiceImpl implements UserTransactionalService {

    ApplicationProperties properties;
    UserRepository userRepository;
    RoleService roleService;
    UserMapper userMapper;

    @Override
    public void createAdmins() {
        List<RoleEntity> roles = roleService.findAll();
        List<String> adminAddresses = properties.getAdminAddresses();

        List<String> existingAdmins = userRepository.findByEthereumAddressIn(adminAddresses)
                .stream()
                .map(UserEntity::getEthereumAddress)
                .collect(Collectors.toList());

        List<UserEntity> newAdmins = adminAddresses.stream()
                .distinct()
                .filter(address -> !existingAdmins.contains(address))
                .map(address -> userMapper.toNewUserFromEthereumAddressAndRoles(address, roles))
                .collect(Collectors.toList());

        userRepository.saveAll(newAdmins)
                .forEach(user -> log.info("Admin with address ({}) successfully created.", user.getEthereumAddress()));
        ;
    }

    @Override
    public UserEntity register(String ethereumAddress) {
        RoleEntity roleUser = roleService.findByCode(RoleCode.ROLE_USER);
        UserEntity newUser = userMapper.toNewUserFromEthereumAddressAndRoles(ethereumAddress, Collections.singletonList(roleUser));
        return userRepository.save(newUser);
    }

    @Override
    public UserDto findById(Long id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new NotFoundObjectException("User not found"));
        return userMapper.toUserDto(user);
    }

    @Override
    public UserDto findByEthereumAddress(String ethereumAddress) {
        UserEntity user = userRepository.findByEthereumAddress(ethereumAddress).orElseGet(() -> register(ethereumAddress));
        return userMapper.toUserDto(user);
    }

    @Override
    public void updateNonceByEthereumAddress(String ethereumAddress) {
        UserEntity user = userRepository.findByEthereumAddress(ethereumAddress).orElseGet(() -> register(ethereumAddress));
        user.updateNonce();
        userRepository.save(user);
    }

    @Override
    public Long countUsers() {
        return userRepository.count();
    }

    @Override
    public Set<UserEntity> findRandomUsers(Long requiredCount) {
        Set<Long> quantifiersIds = new HashSet<>();

        List<Long> idsInRepo = userRepository.getAllIds();
        int totalIds = idsInRepo.size();

        while (quantifiersIds.size() < requiredCount) {
            quantifiersIds.add(idsInRepo.get((int) ThreadLocalRandom.current().nextLong(totalIds)));
        }
        return userRepository.findUserEntitiesByIdIn(quantifiersIds);
    }

    @Override
    public UserEntity findUserEntityById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundObjectException("User not found"));
    }

    @Override
    public Page<UserDto> findByAddressOrDiscordTagOrTelegramHandle(String pattern, Pageable pageable) {
        Specification<UserEntity> specification = new UserSearchForAddingQuantifierSpecification(pattern);
        return userRepository.findAll(specification, pageable).map(userMapper::toUserDto);
    }

    @Override
    public UserDto addToQuantPool(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new NotFoundObjectException("User not found"));
        RoleEntity quantifierRole = roleService.findByCode(RoleCode.ROLE_QUANTIFIER);

        if (user.getRoles().stream().anyMatch(role -> role.getCode().equals(RoleCode.ROLE_QUANTIFIER))) {
            throw new ValidationException("User already in quantification pool");
        }

        user.addRole(quantifierRole);
        user = userRepository.save(user);

        return userMapper.toUserDto(user);
    }

    @Override
    public Page<UserDto> findAll(UserFilter filter, Pageable pageable) {
        Specification<UserEntity> specification = new UserFilterSpecification(filter);
        return userRepository.findAll(specification, pageable).map(userMapper::toUserDto);
    }
}
