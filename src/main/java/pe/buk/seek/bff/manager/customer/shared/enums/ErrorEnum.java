/*
 * @(#)EnumError.java
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

import java.io.Serializable;

/**
 * EnumError.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
public interface ErrorEnum extends Serializable {

    /**
     * Obtiene el prefix
     *
     * @return {@link String}
     */
    String getPrefix();

    /**
     * Obtiene el code
     *
     * @return {@link String}
     */
    String getCode();

    /**
     * Obtiene el Message
     *
     * @return {@link String}
     */
    String getMessage();

    /**
     * Obtiene el StandardLogCode
     *
     * @return {@link String}
     */
    String getStandardLogCode();

    /**
     * Obtiene el StandLogMessage
     *
     * @return {@link String}
     */
    String getStandardLogMessage();

}
