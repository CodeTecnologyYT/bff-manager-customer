/*
 * @(#)AuthSignUpRequest.java
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
package pe.buk.seek.bff.manager.customer.auth.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * AuthSignUpRequest.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthSignUpRequest {

    /** email. */
    @NotEmpty(message = "El email es requerido")
    @Pattern(
        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
        message = "El correo debe tener un formato válido"
    )
    private String email;
    /** password. */
    @NotBlank(message = "El password es requerido")
    @Size(min = 4, message = "El password debe tener al menos 4 caracteres")
    private String password;
    /** role. */
    @NotBlank(message = "El role es requerido")
    private String role;

}
