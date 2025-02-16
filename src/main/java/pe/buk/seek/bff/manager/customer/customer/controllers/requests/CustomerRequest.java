/*
 * @(#)CustomerRequest.java
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
package pe.buk.seek.bff.manager.customer.customer.controllers.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * CustomerRequest.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 13-02-2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRequest {

    /** name. */
    @NotBlank(message = "El nombre el requerido")
    private String name;
    /** lastname. */
    @NotBlank(message = "El apellido es requerido")
    private String lastname;
    /** dateBirth. */
    @NotNull(message = "La fecha de nacimiento es requerida")
    @PastOrPresent(message = "La fecha de nacimiento no puede ser una fecha despues de hoy")
    private LocalDate dateBirth;

}
