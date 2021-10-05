package com.praisesystem.backend.configuration.configs.security.jwt;

import com.praisesystem.backend.users.roles.model.RoleEntity;
import com.praisesystem.backend.users.model.UserEntity;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public final class JwtUserFactory {

    public static JwtUser create(UserEntity user) {
        return new JwtUser(
                user.getId(),
                user.getPublicKey(),
                user.getNonce(),
                mapRolesToGrantedAuthorities(new ArrayList<>(user.getRoles()))
        );
    }
    private static List<GrantedAuthority> mapRolesToGrantedAuthorities(List<RoleEntity> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getCode().name())).collect(Collectors.toList());
    }
}
