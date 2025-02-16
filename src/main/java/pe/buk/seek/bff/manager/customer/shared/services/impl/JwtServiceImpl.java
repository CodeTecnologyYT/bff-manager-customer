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
package pe.buk.seek.bff.manager.customer.shared.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pe.buk.seek.bff.manager.customer.shared.services.JwtService;

/**
 * JwtService.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
@Service
public class JwtServiceImpl implements JwtService {

    /** secret. */
    @Value("${security.secret}")
    private String secret;
    /** expireTime. */
    @Value("${security.expire-time}")
    private Long expireTime;

    // -------------------------------------------------------------------
    // -- Métodos Sobreescritos  -----------------------------------------
    // -------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public String generateToken(UserDetails userDetails) {
        return JWT.create()
            .withSubject(userDetails.getUsername())
            .withIssuedAt(new Date())
            .withExpiresAt(new Date(System.currentTimeMillis() + expireTime))
            .sign(Algorithm.HMAC256(secret));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String extractUsername(String token) {
        return JWT.require(Algorithm.HMAC256(secret))
            .build()
            .verify(token)
            .getSubject();
    }

    // -------------------------------------------------------------------
    // -- Métodos Privados  ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Verifica si el token ha expirado.
     *
     * @param token {@link String}
     * @return {@link Boolean}
     */
    private Boolean isTokenExpired(String token) {
        Date expiration = JWT.require(Algorithm.HMAC256(secret))
            .build()
            .verify(token)
            .getExpiresAt();
        return expiration.before(new Date());
    }

}
