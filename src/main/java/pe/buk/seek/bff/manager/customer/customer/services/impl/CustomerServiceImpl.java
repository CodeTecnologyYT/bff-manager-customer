/*
 * @(#)CustomerServiceImpl.java
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
package pe.buk.seek.bff.manager.customer.customer.services.impl;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pe.buk.seek.bff.manager.customer.customer.controllers.requests.CustomerRequest;
import pe.buk.seek.bff.manager.customer.customer.controllers.responses.CustomerResponse;
import pe.buk.seek.bff.manager.customer.customer.mappers.CustomerMapper;
import pe.buk.seek.bff.manager.customer.customer.repositories.CustomerRepository;
import pe.buk.seek.bff.manager.customer.customer.services.CustomerService;
import pe.buk.seek.bff.manager.customer.customer.utils.CustomerUtils;
import pe.buk.seek.bff.manager.customer.shared.enums.CustomerErrorEnum;
import pe.buk.seek.bff.manager.customer.shared.enums.ErrorEnum;
import pe.buk.seek.bff.manager.customer.shared.exceptions.SimpleException;

/**
 * CustomerServiceImpl.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 13-02-2025
 */
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    /** customerRepository. */
    private final CustomerRepository customerRepository;
    /** customerMapper. */
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    // -------------------------------------------------------------------
    // -- Métodos Publicos  ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerResponse createCustomer(final CustomerRequest request) {
        final var age = CustomerUtils.calculateAge(request.getDateBirth());
        final var customerEntity = this.customerMapper.fromRequestToEntity(request);
        final var customerEntityAddAge = this.customerMapper.addAgeEntity(customerEntity, age);
        final var customerSave = this.customerRepository.save(customerEntityAddAge);
        return this.customerMapper.fromEntityToResponse(customerSave);
    }

    /**
     * {@inheritDoc}
     **/
    @Override
    public CustomerResponse getCustomerById(final Long id) {
        final var customerEntity = this.customerRepository.findById(id)
            .orElseThrow(()-> new SimpleException(CustomerErrorEnum.ERROR_NOT_FOUND_CUSTOMER,
                HttpStatus.NOT_FOUND.value()));
        return this.customerMapper.fromEntityToResponse(customerEntity);
    }

}
