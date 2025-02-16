/*
 * @(#)CustomerFixture.java
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
package pe.buk.seek.bff.manager.customer.customer.fixtures;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import pe.buk.seek.bff.manager.customer.customer.controllers.requests.CustomerRequest;
import pe.buk.seek.bff.manager.customer.customer.entities.CustomerEntity;

/**
 * CustomerFixture.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 15-02-2025
 */
public final class CustomerFixture {

    /** YEAR_DEATH. */
    public static final Integer YEAR_DEATH = 100;
    /** YEAR_WITHDRAWAL. */
    public static final Integer YEAR_WITHDRAWAL = 80;

    // -------------------------------------------------------------------
    // -- Métodos Publicos  ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Obtener una entidad de empleado para pruebas
     *
     * @return {@link CustomerEntity}
     */
    public static CustomerEntity getCustomerEntity(){
        return CustomerEntity.builder()
            .id(1L)
            .age(12)
            .name("Bryan")
            .dateBirth(LocalDate.of(1990, 12, 12))
            .dateWithDrawal(LocalDate.of(2025, 12, 12))
            .dateDeath(LocalDate.of(2025, 12, 12))
            .createdAt(LocalDateTime.now())
            .build();
    }

    /**
     * Obtener una pagina de empleado para pruebas
     *
     * @return {@link CustomerEntity}
     */
    public static Page<CustomerEntity> getPageCustomerEntity(){
        final var customers = List.of(CustomerFixture.getCustomerEntity());
        final var pageable = PageRequest.of(0, 2);
        return new PageImpl<>(customers, pageable, customers.size());
    }

    /**
     * Obtener una pagina de empleado para pruebas
     *
     * @return {@link CustomerEntity}
     */
    public static CustomerRequest getCustomerRequest(){
        return CustomerRequest.builder()
            .name("Bryan")
            .lastname("Rosas")
            .dateBirth(LocalDate.of(1990, 12, 12))
            .build();
    }

    // -------------------------------------------------------------------
    // -- Constructor ----------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Constructor.
     */
    private CustomerFixture() {
        throw new UnsupportedOperationException("Prohibido Instanciar esta clase");
    }
}
