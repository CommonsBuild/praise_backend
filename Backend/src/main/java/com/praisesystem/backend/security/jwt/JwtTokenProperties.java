package com.praisesystem.backend.security.jwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "jwt.token")
public class JwtTokenProperties {
    @NotBlank
    String secret;

    @NotNull
    @Positive
    Long expirationTimeMs;
}
