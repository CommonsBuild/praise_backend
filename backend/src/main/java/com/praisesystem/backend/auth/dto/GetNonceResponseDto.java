package com.praisesystem.backend.auth.dto;

import lombok.Value;

@Value
public class GetNonceResponseDto {
    String publicKey;
    String nonce;
}
