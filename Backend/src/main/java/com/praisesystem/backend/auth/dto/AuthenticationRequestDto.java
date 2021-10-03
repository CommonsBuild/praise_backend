package com.praisesystem.backend.auth.dto;

import com.praisesystem.backend.common.validators.EthereumAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
@Schema(description = "Authentication request object")
public class AuthenticationRequestDto {
    @NotBlank
    @EthereumAddress
    // TODO: 03.10.2021 create custom ethAddress validator 
    @Schema(description = "Ethereum public key", required = true)
    String publicKey;

    @NotBlank
    @Schema(description = "Message which was signed", required = true)
    String message;

    @NotBlank
    @Schema(description = "Signed message signature", required = true)
    String signature;
}
