package com.praisesystem.backend.users.controller;

import com.praisesystem.backend.users.dto.request.UserLimitedFilter;
import com.praisesystem.backend.users.dto.response.UserLimitedDto;
import com.praisesystem.backend.users.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "[USER CONTROLLER]")
@RestController
@RequestMapping(value = "/api/users")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {

    UserService userService;

    @Operation(description = "Find user by ID", security = @SecurityRequirement(name = "jwt"))
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserLimitedDto findById(@PathVariable("id") Long id) {
        return userService.findLimitedUserInfoById(id);
    }

    @Operation(description = "Find all users", security = @SecurityRequirement(name = "jwt"))
    @GetMapping(value ="/allByFilter", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<UserLimitedDto> findAll(
            @ParameterObject UserLimitedFilter filter,
            @ParameterObject Pageable pageable
    ) {
        return userService.findAllLimited(filter, pageable);
    }

}
