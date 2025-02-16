/*
 * @(#)TestCustomerServiceImpl.java
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

import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;
import pe.buk.seek.bff.manager.customer.customer.entities.CustomerEntity;
import pe.buk.seek.bff.manager.customer.customer.fixtures.CustomerFixture;
import pe.buk.seek.bff.manager.customer.customer.properties.CustomerProperties;
import pe.buk.seek.bff.manager.customer.customer.repositories.CustomerRepository;

/**
 * TestCustomerServiceImpl.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 15-02-2025
 */
class TestCustomerServiceImpl {

    /** customerRepository. */
    @Mock
    private CustomerRepository customerRepository;
    /** customerProperties. */
    @Mock
    private CustomerProperties customerProperties;
    /** executorService. */
    @Mock
    private ExecutorService executorService;
    /** customerService. */
    @InjectMocks
    private CustomerServiceImpl instance;

    // -------------------------------------------------------------------
    // -- Setup ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Setup.
     *
     * @throws Exception Exception
     */
    @BeforeEach
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    // -------------------------------------------------------------------
    // -- Tests ----------------------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Test.
     */
    @Test
    void testCreateCustomer() {
        Mockito.doAnswer(invocation -> {
            Runnable task = invocation.getArgument(0);
            task.run();
            return null;
        }).when(this.executorService).execute(ArgumentMatchers.any(Runnable.class));
        Mockito.when(this.customerRepository.save(ArgumentMatchers.any(CustomerEntity.class)))
            .thenReturn(CustomerFixture.getCustomerEntity());
        Mockito.when(this.customerProperties.getEstimatedDeathYear())
            .thenReturn(CustomerFixture.YEAR_DEATH);
        Mockito.when(this.customerProperties.getEstimatedWithDrawalYear())
            .thenReturn(CustomerFixture.YEAR_WITHDRAWAL);
        final var customer = this.instance.createCustomer(CustomerFixture.getCustomerRequest());
        Awaitility.await().atMost(10, TimeUnit.SECONDS)
            .untilAsserted(() -> Mockito.verify(customerRepository, Mockito.times(2))
                .save(ArgumentMatchers.any(CustomerEntity.class)));
        Assertions.assertThat(customer).isNotNull();
        Assertions.assertThat(customer.getId()).isEqualTo(CustomerFixture.getCustomerEntity().getId());
        Mockito.verify(customerRepository, Mockito.times(2))
            .save(ArgumentMatchers.any(CustomerEntity.class));
    }

    /**
     * Test.
     */
    @Test
    void testGetCustomerById() {
        Mockito.when(this.customerRepository.findById(Mockito.anyLong()))
            .thenReturn(Optional.ofNullable(CustomerFixture.getCustomerEntity()));
        final var customer = this.instance.getCustomerById(1L);
        Mockito.verify(this.customerRepository, Mockito.times(1))
            .findById(Mockito.anyLong());
        Assertions.assertThat(CustomerFixture.getCustomerEntity().getId()).isEqualTo(customer.getId());
    }

    /**
     * Test.
     */
    @Test
    void testGetAllCustomers() {
        Mockito.when(this.customerRepository.findAll(ArgumentMatchers.any(PageRequest.class)))
            .thenReturn(CustomerFixture.getPageCustomerEntity());
        final var customerPage = this.instance.getAllCustomers(PageRequest.of(0, 10));
        Assertions.assertThat(CustomerFixture.getPageCustomerEntity().getTotalElements()).isEqualTo(
            customerPage.getTotalElements());
        Assertions.assertThat(CustomerFixture.getPageCustomerEntity().getTotalPages()).isEqualTo(
            customerPage.getTotalPages());
        Assertions.assertThat(customerPage.getContent()).usingRecursiveComparison()
            .ignoringFields("createdAt")
            .isEqualTo(CustomerFixture.getPageCustomerEntity().getContent());
    }

}