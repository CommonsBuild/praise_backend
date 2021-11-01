package com.praisesystem.backend.configuration.configs.security;

import com.praisesystem.backend.configuration.configs.security.jwt.JwtConfigurer;
import com.praisesystem.backend.configuration.configs.security.jwt.JwtTokenProvider;
import com.praisesystem.backend.configuration.properties.ApplicationProperties;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    Environment environment;
    ApplicationProperties properties;
    JwtTokenProvider jwtTokenProvider;

    static String ADMIN_ENDPOINT = "/api/admin/**";
    static String LOGIN_ENDPOINT = "/api/auth/**";
    static String[] SWAGGER_ENDPOINTS = {
            "/v2/api-docs/**",
            "/v3/api-docs/**",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/webjars/**"
    };
//    static String QUANTIFICATION_ENDPOINT = "/api/quantification/**";


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        List<String> profiles = Arrays.asList(environment.getActiveProfiles());

        if (profiles.contains("local") && !profiles.contains("prod")) {
            web.ignoring().antMatchers(SWAGGER_ENDPOINTS);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new NoPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                cors().and()
                // disable basic http security
                .httpBasic().disable()
                // disable csrf
                .csrf().disable()
                // session creation policy STATELESS
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // allow all requests
                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                .antMatchers(LOGIN_ENDPOINT).permitAll()
                // allow for only admin
                .antMatchers(ADMIN_ENDPOINT).hasRole("ADMIN")
                .antMatchers("/api/period/**").permitAll()
                .antMatchers("/api/praise/**").permitAll() // TODO: 25.10.2021 add cert auth
                .antMatchers("/api/quantification/**").hasRole("QUANTIFIER")
                .anyRequest().authenticated()
                .and()
                .apply(new JwtConfigurer(jwtTokenProvider));
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*");
    }

//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Collections.singletonList("*"));
//        configuration.setAllowedMethods(Collections.singletonList("*"));
//        configuration.setAllowedHeaders(Collections.singletonList("*"));
//        configuration.setAllowCredentials(true);
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }

}
