/*
 * @(#)UserDetailsService.java
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
import pe.buk.seek.bff.manager.customer.auth.repositories.RoleRepository;
import pe.buk.seek.bff.manager.customer.auth.repositories.UserRepository;
import pe.buk.seek.bff.manager.customer.shared.enums.CustomerErrorEnum;
import pe.buk.seek.bff.manager.customer.shared.exceptions.SimpleException;
import pe.buk.seek.bff.manager.customer.shared.services.JwtService;

/**
 * UserDetailsService.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 15-02-2025
 */
@Service
@RequiredArgsConstructor
public class UserDetailsAuthService implements UserDetailsService {

    /** userRepository. */
    private final UserRepository userRepository;

    // -------------------------------------------------------------------
    // -- Métodos Publicos  ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        final var user = userRepository.findByEmail(email)
            .orElseThrow(
                () -> new SimpleException(CustomerErrorEnum.ERROR_NOT_FOUND_USER, HttpStatus.NOT_FOUND.value()));

        return User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .roles(user.getRole().getName())
            .build();
    }

}
