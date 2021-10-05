package com.praisesystem.backend.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Get nonce response object")
public class GetNonceResponseDto {
    @Schema(description = "Public key")
    String publicKey;
    @Schema(description = "Nonce")
    String nonce;
}
