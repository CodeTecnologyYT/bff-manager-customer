/*
 * @(#)CustomerMapper.java
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
package pe.buk.seek.bff.manager.customer.customer.mappers;

import java.time.LocalDate;
import java.util.List;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import pe.buk.seek.bff.manager.customer.customer.controllers.requests.CustomerRequest;
import pe.buk.seek.bff.manager.customer.customer.controllers.responses.CustomerResponse;
import pe.buk.seek.bff.manager.customer.customer.controllers.responses.CustomerShortResponse;
import pe.buk.seek.bff.manager.customer.customer.entities.CustomerEntity;

/**
 * CustomerMapper.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 13-02-2025
 */
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    /**
     * Convierte lista de entidad {@link CustomerEntity} a {@link CustomerResponse}
     *
     * @param entities {@link CustomerEntity}
     * @return lista {@link CustomerResponse}
     */
    List<CustomerResponse> fromEntitiesToResponses(List<CustomerEntity> entities);

    /**
     * Convierte objeto de entidad {@link CustomerEntity} a {@link CustomerResponse}
     *
     * @param entity {@link CustomerEntity}
     * @return {@link CustomerResponse}
     */
    CustomerResponse fromEntityToResponse(CustomerEntity entity);

    /**
     * Convierte objeto de entidad {@link CustomerEntity} a {@link CustomerShortResponse}
     *
     * @param entity {@link CustomerEntity}
     * @return {@link CustomerShortResponse}
     */
    CustomerShortResponse fromEntityToResponseShort(CustomerEntity entity);

    /**
     * Convierte request {@link CustomerRequest} a {@link CustomerEntity}
     *
     * @param request {@link CustomerRequest}
     * @return {@link CustomerEntity}
     */
    CustomerEntity fromRequestToEntity(CustomerRequest request);

    /**
     * Setea la informacion de la edad en la entidad
     *
     * @param entity {@link CustomerEntity}
     * @param ageCustomer {@link Integer}
     * @return {@link CustomerEntity}
     */
    @Mapping(target = "age", source = "ageCustomer")
    @BeanMapping(ignoreByDefault = true)
    CustomerEntity addAgeEntity(@MappingTarget CustomerEntity entity, Integer ageCustomer);

    /**
     * Setea la informacion de la fecha esperanza de vida y fecha de retiro en la entidad
     *
     * @param entity {@link CustomerEntity}
     * @param dateDeathCustomer {@link LocalDate}
     * @param dateWithDrawalCustomer {@link LocalDate}
     * @return {@link CustomerEntity}
     */
    @Mapping(target = "dateDeath", source = "dateDeathCustomer")
    @Mapping(target = "dateWithDrawal", source = "dateWithDrawalCustomer")
    @BeanMapping(ignoreByDefault = true)
    CustomerEntity addAgeEntity(@MappingTarget CustomerEntity entity, LocalDate dateDeathCustomer,
        LocalDate dateWithDrawalCustomer);

}