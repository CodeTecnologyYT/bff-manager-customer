/*
 * @(#)UserServiceImpl.java
 *
 * Copyright (c) SEEK (Chile). All rights reserved.
 *
 * All rights to this product are owned by SEEK and may only
 * be used under the terms of its associated license document. You may NOT
 * copy, modify, sublicense, or distribute this source file or portions of
 * it unless previously authorized in writing by SEEK.
 * In any event, this notice and the above copyright must always be included
 * verbatim with this file.
 */
package pe.buk.seek.bff.manager.customer.auth.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.buk.seek.bff.manager.customer.shared.enums.CustomerErrorEnum;
import pe.buk.seek.bff.manager.customer.shared.exceptions.SimpleException;
import pe.buk.seek.bff.manager.customer.shared.services.JwtService;
import pe.buk.seek.bff.manager.customer.auth.controllers.requests.AuthSignUpRequest;
import pe.buk.seek.bff.manager.customer.auth.controllers.responses.AuthUserResponse;
import pe.buk.seek.bff.manager.customer.auth.entities.UserEntity;
import pe.buk.seek.bff.manager.customer.auth.repositories.RoleRepository;
import pe.buk.seek.bff.manager.customer.auth.repositories.UserRepository;
import pe.buk.seek.bff.manager.customer.auth.services.AuthService;

/**
 * UserServiceImpl.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    /** userRepository. */
    private final UserRepository userRepository;
    /** roleRepository. */
    private final RoleRepository roleRepository;
    /** passwordEncoder. */
    private final PasswordEncoder passwordEncoder;
    /** jwtService. */
    private final JwtService jwtService;

    // -------------------------------------------------------------------
    // -- Métodos Publicos  ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public AuthUserResponse loginUser(final String email) throws UsernameNotFoundException {
        // Busca si el usuario existe
        final var user = userRepository.findByEmail(email)
            .orElseThrow(
                () -> new SimpleException(CustomerErrorEnum.ERROR_NOT_FOUND_USER, HttpStatus.NOT_FOUND.value()));
        // Genera la respuesta y el token
        final var userDetails = User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .roles(user.getRole().getName())
            .build();
        return AuthUserResponse.builder()
            .email(email)
            .token(this.jwtService.generateToken(userDetails))
            .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthUserResponse registerUser(final AuthSignUpRequest signUpRequest) {

        // Verficar si el email existe
        if (this.userRepository.findByEmail(signUpRequest.getEmail()).isPresent()) {
            throw new SimpleException(CustomerErrorEnum.ERROR_EXITS_USER, HttpStatus.BAD_REQUEST.value());
        }
        //Verificamos si existe el role
        final var role = this.roleRepository.findByName(signUpRequest.getRole())
            .orElseThrow(
                () -> new SimpleException(CustomerErrorEnum.ERROR_NOT_FOUND_ROLE, HttpStatus.BAD_REQUEST.value()));

        // Crea un nuevo usuario
        final var user = UserEntity.builder()
            .email(signUpRequest.getEmail())
            .password(passwordEncoder.encode(signUpRequest.getPassword()))
            .role(role)
            .build();

        this.userRepository.save(user);

        // Generate Token
        final var userDetails = User.builder()
            .username(signUpRequest.getEmail())
            .password(user.getPassword())
            .roles(user.getRole().getName())
            .build();

        return AuthUserResponse
            .builder()
            .email(signUpRequest.getEmail())
            .token(this.jwtService.generateToken(userDetails))
            .build();
    }

}
