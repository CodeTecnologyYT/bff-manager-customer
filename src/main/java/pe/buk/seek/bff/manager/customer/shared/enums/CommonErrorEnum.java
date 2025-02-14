/*
 * @(#)CommonEnum.java
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

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * CommonEnum.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
@Getter
@AllArgsConstructor
public enum CommonErrorEnum implements ErrorEnum {

    /** DEFAULT. */
    DEFAULT("99", "Error generico"),
    DATABASE("11", "Error con la base de datos"),
    ERROR_DATA_REQUEST("100","Error de datos");

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
    public String getCode() {
        String var10000 = this.getPrefix();
        return var10000 + this.code;
    }

    /**
     * {@inheritDoc}
     */
    public String getPrefix() {
        return PrefixEnum.BMC.name();
    }

    /**
     * {@inheritDoc}
     */
    public String getStandardLogCode() {
        return this.code;
    }

    /**
     * {@inheritDoc}
     */
    public String getStandardLogMessage() {
        return this.message;
    }

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Obtiene el codigo CommonError
     *
     * @param code {@link String}
     * @return {@link CommonErrorEnum}
     */
    public static CommonErrorEnum getErrorByCode(final String code) {
        for(CommonErrorEnum error : values()) {
            if (error.getCode().equalsIgnoreCase(code)) {
                return error;
            }
        }
        return DEFAULT;
    }

}