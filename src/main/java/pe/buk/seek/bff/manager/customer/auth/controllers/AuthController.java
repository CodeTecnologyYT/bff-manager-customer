/*
 * @(#)AuthController.java
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
package pe.buk.seek.bff.manager.customer.auth.controllers;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.buk.seek.bff.manager.customer.auth.controllers.requests.AuthLoginRequest;
import pe.buk.seek.bff.manager.customer.auth.controllers.requests.AuthSignUpRequest;
import pe.buk.seek.bff.manager.customer.auth.controllers.responses.AuthUserResponse;
import pe.buk.seek.bff.manager.customer.auth.services.AuthService;
import pe.buk.seek.bff.manager.customer.shared.constants.CustomerConstant;
import pe.buk.seek.bff.manager.customer.shared.enums.CustomerErrorEnum;
import pe.buk.seek.bff.manager.customer.shared.exceptions.SimpleException;

/**
 * AuthController.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "Seguridad", description = "API de autenticacion")
public class AuthController {

    /** authService. */
    private final AuthService authService;
    /** authService. */
    private final AuthenticationManager authenticationManager;

    // -------------------------------------------------------------------
    // -- Métodos Publicos  ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Endpoint de creacion de usuarios
     *
     * @param request {@link AuthSignUpRequest}
     * @return {@link AuthUserResponse}
     */
    @PostMapping("/login")
    @ApiResponse(responseCode = CustomerConstant.APICODE_200,
        description = "Exito al logearte en el servicio")
    @ApiResponse(responseCode = CustomerConstant.APICODE_400, description = CustomerConstant.ERROR_DATOS,
        content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = CustomerConstant.APICODE_500, description = CustomerConstant.ERROR_NO_ESPERADO,
        content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<AuthUserResponse> login(@Valid @RequestBody final AuthLoginRequest request) {
        final var authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        if (!authentication.isAuthenticated()) {
            throw new SimpleException(CustomerErrorEnum.ERROR_AUTENTICACION_USER, HttpStatus.BAD_REQUEST.value());
        }
        final var authUserResponse = authService.loginUser(request.getEmail());
        return ResponseEntity.ok(authUserResponse);
    }

    /**
     * Endpoint de creacion de usuarios
     *
     * @param request {@link AuthSignUpRequest}
     * @return {@link AuthUserResponse}
     */
    @PostMapping("/signup")
    @ApiResponse(responseCode = CustomerConstant.APICODE_200,
        description = "Exito al crear un usuario")
    @ApiResponse(responseCode = CustomerConstant.APICODE_400, description = CustomerConstant.ERROR_DATOS,
        content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = CustomerConstant.APICODE_500, description = CustomerConstant.ERROR_NO_ESPERADO,
        content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<AuthUserResponse> signUp(@Valid @RequestBody final AuthSignUpRequest request) {
        final var authUserResponse = authService.registerUser(request);
        return ResponseEntity.ok(authUserResponse);
    }

}
