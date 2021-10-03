package com.praisesystem.backend.users.dto;

import lombok.Value;

import java.util.List;

@Value
public class UserDto {
    Long id;
    String publicKey;
    List<String> roles;
}
