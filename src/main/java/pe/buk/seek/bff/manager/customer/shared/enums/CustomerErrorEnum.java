/*
 * @(#)CustomerErrorEnum.java
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
package pe.buk.seek.bff.manager.customer.shared.enums;

import lombok.RequiredArgsConstructor;

/**
 * CustomerErrorEnum.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
@RequiredArgsConstructor
public enum CustomerErrorEnum implements ErrorEnum {

    /** DEFAULT. */
    DEFAULT("99", "Error Generico"),
    /** ERROR_NOT_FOUND_CUSTOMER. */
    ERROR_NOT_FOUND_CUSTOMER("420", "No se encontro el empleador"),
    /** ERROR_NOT_FOUND_USER. */
    ERROR_NOT_FOUND_USER("420", "Usuario no se encontro"),
    /** ERROR_EXITS_USER. */
    ERROR_EXITS_USER("420", "Ya existe un usuario con ese correo"),
    /** ERROR_NOT_FOUND_ROLE. */
    ERROR_NOT_FOUND_ROLE("420", "Role no se encontro"),
    /** ERROR_AUTENTICACION_USER. */
    ERROR_AUTENTICACION_USER("420", "Credenciales invalidads");

    /** code. */
    private final String code;
    /** message. */
    private final String message;

    // -------------------------------------------------------------------
    // -- Métodos Sobrescritos -------------------------------------------
    // -------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public String getCode() {
        return this.getPrefix() + this.code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPrefix() {
        return "BFFBENE";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStandardLogCode() {
        return this.code;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getStandardLogMessage() {
        return this.message;
    }

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Obtiene enum de Error segun parametro.
     *
     * @param code {@link String}
     * @return {@link CustomerErrorEnum}
     */
    public static CustomerErrorEnum getErrorByCode(final String code) {
        for (final CustomerErrorEnum error : CustomerErrorEnum.values()) {
            if (error.getCode().equalsIgnoreCase(code)) {
                return error;
            }
        }
        return DEFAULT;
    }

}
