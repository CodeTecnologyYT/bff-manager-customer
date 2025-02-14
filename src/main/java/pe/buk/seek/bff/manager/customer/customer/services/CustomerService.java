/*
 * @(#)CustomerService.java
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
package pe.buk.seek.bff.manager.customer.customer.services;

import pe.buk.seek.bff.manager.customer.customer.controllers.requests.CustomerRequest;
import pe.buk.seek.bff.manager.customer.customer.controllers.responses.CustomerResponse;

/**
 * CustomerService.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 13-02-2025
 */
public interface CustomerService {

    /**
     * Crea un empleador
     *
     * @param request {@link CustomerRequest}
     * @return {@link CustomerResponse}
     */
    CustomerResponse createCustomer(CustomerRequest request);

    /**
     * Obtiene un empleador por el id
     *
     * @param id {@link Long}
     * @return {@link CustomerResponse}
     */
    CustomerResponse getCustomerById(Long id);

}