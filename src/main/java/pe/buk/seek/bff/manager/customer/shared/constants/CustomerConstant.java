/*
 * @(#)CustomerConstant.java
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
package pe.buk.seek.bff.manager.customer.shared.constants;

/**
 * CustomerConstant.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
public final class CustomerConstant {

    // -------------------------------------------------------------------
    // -- SWAGGER --------------------------------------------------------
    // -------------------------------------------------------------------
    /** ERROR_AL_AUTORIZAR. */
    public static final String ERROR_AL_AUTORIZAR = "Error al Autorizar";
    /** ERROR_NO_ESPERADO. */
    public static final String ERROR_NO_ESPERADO = "Error no esperado";
    /** SPRINGDDOC API STATUS CODE 200 **/
    public static final String APICODE_200 = "200";
    /** SPRINGDDOC API STATUS CODE 201 **/
    public static final String APICODE_201 = "201";
    /** SPRINGDDOC API STATUS CODE 400 **/
    public static final String APICODE_400 = "400";
    /** SPRINGDDOC API STATUS CODE 403 **/
    public static final String APICODE_403 = "403";
    /** SPRINGDDOC API STATUS CODE 404 **/
    public static final String APICODE_404 = "404";
    /** SPRINGDDOC API STATUS CODE 503 **/
    public static final String APICODE_500 = "503";

    // -------------------------------------------------------------------
    // -- Constructor ----------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Constructor.
     */
    private CustomerConstant() {
        throw new UnsupportedOperationException("Prohibido Instanciar esta clase");
    }

}
