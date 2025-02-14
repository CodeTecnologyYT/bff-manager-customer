/*
 * @(#)JwtService.java
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
package pe.buk.seek.bff.manager.customer.shared.services;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * JwtService.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
public interface JwtService {

    /**
     * Genera un JWT para un usuario.
     *
     * @param userDetails {@link UserDetails}
     * @return  {@link String}
     */
    String generateToken(UserDetails userDetails);

    /**
     * Valida un JWT y extrae el usuario.
     *
     * @param token {@link String}
     * @param userDetails {@link UserDetails}
     * @return {@link Boolean}
     */
    Boolean validateToken(String token, UserDetails userDetails);

    /**
     * Extrae el usuario desde el token.
     *
     * @param token {@link String}
     * @return {@link String}
     */
    String extractUsername(String token);

}