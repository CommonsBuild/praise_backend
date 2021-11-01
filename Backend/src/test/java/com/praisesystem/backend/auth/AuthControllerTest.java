package com.praisesystem.backend.auth;

import com.praisesystem.backend.auth.dto.GetNonceResponseDto;
import com.praisesystem.backend.configuration.configs.security.jwt.JwtTokenProvider;
import com.praisesystem.backend.users.dto.response.UserDto;
import com.praisesystem.backend.users.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtTokenProvider jwtTokenProvider;
    @Mock
    UserService userService;

    AuthController controller;

    @BeforeEach
    void setUp() {
        controller = new AuthController(authenticationManager, jwtTokenProvider, userService);
    }

    @Test
    void auth() {
    }

    @Test
    @DisplayName("valid get nonce request")
    void nonce() {
        // STEP1. firstly I created objects for my mocked methods.
        UserDto user = new UserDto(
                0L,
                "0x123",
                List.of("123"),
                "123"
        );

        // STEP2. Say to mockito mocks to return my defined object when findByEthereumAddress called with any string as argument
        when(userService.findByEthereumAddress(anyString())).thenReturn(user);

        // STEP3. do assertions
        assertThatCode(() -> {
            GetNonceResponseDto response = controller.nonce("0x123");

            // assert response (not always necessary)
            assertThat(response)
                    .hasFieldOrPropertyWithValue("ethereumAddress", user.getEthereumAddress())
                    .hasFieldOrPropertyWithValue("nonce", user.getNonce());
        }).doesNotThrowAnyException(); // verify that controller.nonce and assertions does not throw any exception

        // STEP4 verify that findByEthAddress was called only 1 time with any String argument.
        verify(userService, times(1)).findByEthereumAddress(anyString());
    }
}