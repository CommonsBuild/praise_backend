package com.praisesystem.backend.users.controller;

import com.praisesystem.backend.auth.AuthUtils;
import com.praisesystem.backend.users.dto.request.UserFilter;
import com.praisesystem.backend.users.dto.response.UserDto;
import com.praisesystem.backend.users.roles.enums.RoleCode;
import com.praisesystem.backend.users.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User controller (ADMIN)")
@Slf4j(topic = "[USER ADMIN CONTROLLER]")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/admin/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAdminController {

    UserService userService;

    @Operation(description = "Find all users", security = @SecurityRequirement(name = "jwt"))
    @GetMapping(value ="/allByFilter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserDto> findAll(
            @ParameterObject UserFilter filter,
            @ParameterObject Pageable pageable
    ) {
        return userService.findAll(filter, pageable);
    }

    @Operation(description = "Find user by ethereumAddress or discordTag or telegramHandle", security = @SecurityRequirement(name = "jwt"))
    @GetMapping(value = "/userByAddressOrDiscordTagOrTelegramHandle", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserDto> findByAddressOrDiscordTagOrTelegramHandle(
            @Parameter(description = "Search pattern")
            @RequestParam("pattern") String pattern,
            @ParameterObject Pageable pageable
    ) {
        return userService.findByAddressOrDiscordTagOrTelegramHandle(pattern, pageable);
    }

    @Operation(description = "Find user by ID", security = @SecurityRequirement(name = "jwt"))
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto findById(@PathVariable("id") Long id) {
        return userService.findUserInfoById(id);
    }

    @Operation(description = "Add role to user", security = @SecurityRequirement(name = "jwt"))
    @PatchMapping(value = "/{userId}/addRole", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto addRole(@PathVariable("userId") Long userId, @RequestParam RoleCode code) {
        Long adminId = AuthUtils.getCurrentUser().getId();
        log.info("Request to add new role ({}) to user with ID ({}) from admin with ID ({})", code.getLabel(), userId, adminId);
        return userService.addRole(userId, code);
    }

    @Operation(description = "Remove role from user", security = @SecurityRequirement(name = "jwt"))
    @PatchMapping(value = "/{userId}/removeRole", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto removeRole(@PathVariable("userId") Long userId, @RequestParam RoleCode code) {
        Long adminId = AuthUtils.getCurrentUser().getId();
        log.info("Request to remove role ({}) from user with ID ({}) from admin with ID ({})", code.getLabel(), userId, adminId);
        return userService.removeRole(userId, code);
    }
}
