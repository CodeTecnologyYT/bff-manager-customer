/*
 * @(#)UserService.java
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
package pe.buk.seek.bff.manager.customer.auth.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import pe.buk.seek.bff.manager.customer.auth.controllers.requests.AuthSignUpRequest;
import pe.buk.seek.bff.manager.customer.auth.controllers.responses.AuthUserResponse;

/**
 * UserService.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
public interface AuthService  {

    /**
     * Registra un usuario nuevo
     *
     * @param signUpRequest {@link AuthSignUpRequest}
     * @return {@link AuthUserResponse}
     */
    AuthUserResponse registerUser(AuthSignUpRequest signUpRequest);

    /**
     * Login de los usuarios
     *
     * @param email {@link String}
     * @return {@link AuthUserResponse}
     */
    AuthUserResponse loginUser(String email);

}