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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pe.buk.seek.bff.manager.customer.customer.controllers.requests.CustomerRequest;
import pe.buk.seek.bff.manager.customer.customer.controllers.responses.CustomerResponse;
import pe.buk.seek.bff.manager.customer.customer.controllers.responses.CustomerShortResponse;
import pe.buk.seek.bff.manager.customer.customer.entities.CustomerEntity;
import pe.buk.seek.bff.manager.customer.customer.mappers.CustomerMapper;
import pe.buk.seek.bff.manager.customer.customer.properties.CustomerProperties;
import pe.buk.seek.bff.manager.customer.customer.repositories.CustomerRepository;
import pe.buk.seek.bff.manager.customer.customer.services.CustomerService;
import pe.buk.seek.bff.manager.customer.customer.utils.CustomerUtils;
import pe.buk.seek.bff.manager.customer.shared.enums.CustomerErrorEnum;
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
    /** customerProperties. */
    private final CustomerProperties customerProperties;
    /** executor. */
    private final ExecutorService executor;
    /** customerMapper. */
    private final CustomerMapper customerMapper = Mappers.getMapper(CustomerMapper.class);

    // -------------------------------------------------------------------
    // -- Métodos Publicos  ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerShortResponse createCustomer(final CustomerRequest request) {
        final var age = CustomerUtils.calculateAge(request.getDateBirth());
        final var customerEntity = this.customerMapper.fromRequestToEntity(request);
        final var customerEntityAddAge = this.customerMapper.addAgeEntity(customerEntity, age);
        final var customerSave = this.customerRepository.save(customerEntityAddAge);
        this.asyncSlowCalculateCustomer(request, customerSave);
        return this.customerMapper.fromEntityToResponseShort(customerSave);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Cacheable(value = "customer-id", key = "#id")
    public CustomerResponse getCustomerById(final Long id) {
        final var customerEntity = this.customerRepository.findById(id)
            .orElseThrow(() -> new SimpleException(CustomerErrorEnum.ERROR_NOT_FOUND_CUSTOMER,
                HttpStatus.NOT_FOUND.value()));
        return this.customerMapper.fromEntityToResponse(customerEntity);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<CustomerResponse> getAllCustomers(final Pageable pageable) {
        final var customers = this.customerRepository.findAll(pageable);
        return customers.map(this.customerMapper::fromEntityToResponse);
    }

    /**
     * Procesos pesados de calculo de la fecha
     *
     * @param request {@link CustomerRequest}
     * @param customerEntity {@link CustomerEntity}
     */
    private void asyncSlowCalculateCustomer(final CustomerRequest request, final CustomerEntity customerEntity) {
        CompletableFuture.runAsync(() -> {
            final var estimatedDateDeath = CustomerUtils.calculateEstimateLifeDate(request.getDateBirth(),
                this.customerProperties.getEstimatedDeathYear());
            final var estimatedDateWithDrawal = CustomerUtils.calculateEstimateWithDrawalDate(request.getDateBirth(),
                this.customerProperties.getEstimatedWithDrawalYear());
            final var customerEntityAddAge = this.customerMapper.addAgeEntity(customerEntity, estimatedDateDeath,
                estimatedDateWithDrawal);
            this.customerRepository.save(customerEntityAddAge);
        }, executor);
    }

}
