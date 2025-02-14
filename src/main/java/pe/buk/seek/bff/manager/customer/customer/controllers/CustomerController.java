/*
 * @(#)CustomerController.java
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
package pe.buk.seek.bff.manager.customer.customer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.buk.seek.bff.manager.customer.customer.controllers.requests.CustomerRequest;
import pe.buk.seek.bff.manager.customer.customer.controllers.responses.CustomerResponse;
import pe.buk.seek.bff.manager.customer.customer.services.CustomerService;
import pe.buk.seek.bff.manager.customer.shared.constants.CustomerConstant;

/**
 * CustomerController.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 13-02-2025
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/manager/customer")
@Tag(name = "Empleados", description = "API para gestión de empleados")
public class CustomerController {

    /** customerService. */
    private final CustomerService customerService;

    // -------------------------------------------------------------------
    // -- Métodos Publicos  ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Endpoint de creacion de un empleador
     *
     * @param request {@link CustomerRequest}
     * @return {@link CustomerResponse}
     */
    @PostMapping
    @Operation(summary = "Crear un trabajador", description = "Agrega un nuevo trabajador al sistema")
    @ApiResponse(responseCode = CustomerConstant.APICODE_201,
        description = "Exito al crear el trabajador")
    @ApiResponse(responseCode = CustomerConstant.APICODE_403, description = CustomerConstant.ERROR_AL_AUTORIZAR,
        content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = CustomerConstant.APICODE_500, description = CustomerConstant.ERROR_NO_ESPERADO,
        content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<CustomerResponse> createCustomer(@RequestBody CustomerRequest request) {
        final var response = this.customerService.createCustomer(request);
        return ResponseEntity.created(URI.create("/" + response.getId()))
            .body(response);
    }

    /**
     * Endpoint obtencion del trabajador por el id
     *
     * @param id {@link Long}
     * @return {@link CustomerResponse}
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener un trabajador por Id", description = "Retorna un trabajador")
    @ApiResponse(responseCode = CustomerConstant.APICODE_200,
        description = "Exito al obtener obtener el trabajador por el id")
    @ApiResponse(responseCode = CustomerConstant.APICODE_404,
        description = "No se pudo obtener el trabajador por el id")
    @ApiResponse(responseCode = CustomerConstant.APICODE_403, description = CustomerConstant.ERROR_AL_AUTORIZAR,
        content = @Content(schema = @Schema(hidden = true)))
    @ApiResponse(responseCode = CustomerConstant.APICODE_500, description = CustomerConstant.ERROR_NO_ESPERADO,
        content = @Content(schema = @Schema(hidden = true)))
    public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable("id") Long id) {
        final var response = this.customerService.getCustomerById(id);
        return ResponseEntity.ok(response);
    }

}
