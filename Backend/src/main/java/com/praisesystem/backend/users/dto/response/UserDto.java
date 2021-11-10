package com.praisesystem.backend.users.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.praisesystem.backend.accounts.AccountDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.List;

@Value
@Schema(description = "User response object")
public class UserDto {

    @Schema(description = "User ID")
    Long id;

    @JsonIgnore
    String ethereumAddress;

    @Schema(description = "List of user roles")
    List<String> roles;

    @Schema(description = "User social accounts")
    List<AccountDto> accounts;

    @JsonIgnore
    String nonce;
}
