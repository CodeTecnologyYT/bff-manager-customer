/*
 * @(#)CustomerRepository.java
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
package pe.buk.seek.bff.manager.customer.customer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.buk.seek.bff.manager.customer.customer.entities.CustomerEntity;

/**
 * CustomerRepository.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 13-02-2025
 */
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}