package com.praisesystem.backend.auth;

import com.praisesystem.backend.configuration.configs.security.jwt.JwtUser;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUtils {

    public static JwtUser getCurrentUser() {
        return (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
