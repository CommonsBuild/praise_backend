package com.praisesystem.backend.users.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.List;

@Value
@Schema(description = "User response object")
public class UserDto {
    @Schema(description = "User ID")
    Long id;
    @Schema(description = "User Ethereum address")
    String publicKey;
    @Schema(description = "List of user roles")
    List<String> roles;
    @JsonIgnore
    String nonce;
}
