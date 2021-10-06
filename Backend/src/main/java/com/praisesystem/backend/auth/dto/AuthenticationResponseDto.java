package com.praisesystem.backend.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Authentication response object")
public class AuthenticationResponseDto {
    @Schema(description = "JWT Access token")
    String accessToken;
    @Schema(description = "Public key")
    String ethereumAddress;
    @Schema(description = "Token type")
    String tokenType = "Bearer";
}
