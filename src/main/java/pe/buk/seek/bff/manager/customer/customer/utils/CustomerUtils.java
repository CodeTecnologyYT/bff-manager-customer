/*
 * @(#)CustomerUtils.java
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
package pe.buk.seek.bff.manager.customer.customer.utils;

import java.time.LocalDate;

/**
 * CustomerUtils.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
public final class CustomerUtils {

    // -------------------------------------------------------------------
    // -- Métodos Publicos  ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Calcula la edad en base a la fecha de nacimiento
     *
     * @param dateOfBirth {@link LocalDate}
     * @return {@link Integer}
     */
    public static Integer calculateAge(final LocalDate dateOfBirth) {
        final var today = LocalDate.now();
        return today.getYear() - dateOfBirth.getYear();
    }

    /**
     * Calcula la fecha de Esperanza de vida en base a la fecha de nacimiento
     *
     * @param dateOfBirth {@link LocalDate}
     * @return {@link LocalDate}
     */
    public static LocalDate calculateLifeDate(final LocalDate dateOfBirth, final Integer ageDeath) {
        return dateOfBirth.plusYears(ageDeath);
    }

    /**
     * Calcula la fecha en la se retira del trabajo segun la fecha de nacimiento
     *
     * @param dateOfBirth {@link LocalDate}
     * @param ageWithDrawal {@link Integer}
     * @return {@link LocalDate}
     */
    public static LocalDate calculateWithDrawalDate(final LocalDate dateOfBirth, final Integer ageWithDrawal) {
        return dateOfBirth.plusYears(ageWithDrawal);
    }

    // -------------------------------------------------------------------
    // -- Constructor ----------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Constructor.
     */
    private CustomerUtils() {
        throw new UnsupportedOperationException("Prohibido Instanciar esta clase");
    }

}
