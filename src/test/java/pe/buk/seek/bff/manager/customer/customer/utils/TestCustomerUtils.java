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
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * CustomerUtils.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 15-02-2025
 */
public class TestCustomerUtils {

    // -------------------------------------------------------------------
    // -- Tests ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Test.
     */
    @Test
    void testAgeCustomer() {
        final var edad = LocalDate.now().getYear() - 2010;
        Assertions.assertThat(CustomerUtils.calculateAge(LocalDate.of(2010, 7, 1)))
            .isEqualTo(edad);
    }

    /**
     * Test.
     */
    @Test
    void testYearDeathCustomer() {
        Assertions.assertThat(CustomerUtils.calculateEstimateLifeDate(LocalDate.of(2010, 7, 1), 80))
            .isEqualTo(LocalDate.of(2090, 7, 1));
    }

    /**
     * Test.
     */
    @Test
    void testYearWithDrawalCustomer() {
        Assertions.assertThat(CustomerUtils.calculateEstimateWithDrawalDate(LocalDate.of(2010, 7, 1), 80))
            .isEqualTo(LocalDate.of(2090, 7, 1));
    }

}
