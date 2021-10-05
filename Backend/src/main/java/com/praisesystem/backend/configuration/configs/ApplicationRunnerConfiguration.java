package com.praisesystem.backend.configuration.configs;

import com.praisesystem.backend.users.roles.RoleService;
import com.praisesystem.backend.users.roles.enums.RoleCode;
import com.praisesystem.backend.users.roles.model.RoleEntity;
import com.praisesystem.backend.users.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Configuration
public class ApplicationRunnerConfiguration {

    @Bean
    ApplicationRunner createOrUpdateRoles(
            RoleService roleService,
            UserService userService
    ) {
        return applicationArguments -> {
            createRoles(roleService);
            createAdmin(userService);

        };
    }

    private void createRoles(RoleService roleService) {
        log.info("[APPLICATION RUNNER] The process of creating roles has started");
        List<RoleEntity> roles = roleService.findAll();
        Stream.of(RoleCode.values())
                .filter(code -> !roles.stream().map(RoleEntity::getCode)
                        .collect(Collectors.toList())
                        .contains(code)
                ).forEach(roleService::createByRoleCode);
        log.info("[APPLICATION RUNNER] The process of creating roles has been completed");
    }

    private void createAdmin(UserService userService) {
        log.info("[APPLICATION RUNNER] The process of creating admin account has started");
        userService.createAdmin();
        log.info("[APPLICATION RUNNER] The process of creating admin account has been completed");
    }
}
