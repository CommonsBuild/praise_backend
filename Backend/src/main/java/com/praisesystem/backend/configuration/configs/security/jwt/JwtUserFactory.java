package com.praisesystem.backend.configuration.configs.security.jwt;

import com.praisesystem.backend.users.dto.response.UserDto;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(UserDto user) {
        return new JwtUser(
                user.getId(),
                user.getEthereumAddress(),
                user.getNonce(),
                mapRolesToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }

    private static List<GrantedAuthority> mapRolesToGrantedAuthorities(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
