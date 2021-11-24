package com.praisesystem.backend.users.dto.response;

import com.praisesystem.backend.accounts.dto.AccountDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import java.util.List;

@Value
public class UserLimitedDto {
    @Schema(description = "User ID")
    Long id;

    @Schema(description = "List of user roles")
    List<String> roles;

    @Schema(description = "User social accounts")
    List<AccountDto> accounts;
}
