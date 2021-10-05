package com.praisesystem.backend.configuration.configs.security.jwt;

import com.praisesystem.backend.users.dto.UserDto;
import com.praisesystem.backend.users.services.UserService;
import com.praisesystem.backend.users.model.UserEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtUserDetailsService implements UserDetailsService {

    UserService service;

    @Override
    public UserDetails loadUserByUsername(String address) throws UsernameNotFoundException {
        UserDto user = service.findByPublicKey(address);
        return JwtUserFactory.create(user);
    }
}
