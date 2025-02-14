/*
 * @(#)GlobalExceptionConfig.java
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
package pe.buk.seek.bff.manager.customer.shared.config;

import jakarta.persistence.EntityNotFoundException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pe.buk.seek.bff.manager.customer.shared.enums.CommonErrorEnum;
import pe.buk.seek.bff.manager.customer.shared.exceptions.SimpleException;
import pe.buk.seek.bff.manager.customer.shared.responses.SimpleErrorResponse;

/**
 * GlobalExceptionConfig.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionConfig extends ResponseEntityExceptionHandler {

    /**
     * Excepcion para una excepcion cualquiera
     *
     * @param ex {@link Exception}
     * @return {@link  SimpleErrorResponse}
     */
    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<SimpleErrorResponse> handleExceptionInternal(Exception ex) {
        log.error("BFF-MANAGER-CUSTOMER_EXCEPTION-GENERAL:", ex);
        return new ResponseEntity(SimpleErrorResponse.builder()
            .code(CommonErrorEnum.DEFAULT.getCode())
            .message(ex.getMessage())
            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Excepcion para una excepcion cualquiera
     *
     * @param ex {@link Exception}
     * @return {@link  SimpleErrorResponse}
     */
    @ExceptionHandler({ DataAccessException.class, JpaSystemException.class, ConstraintViolationException.class,
        SQLIntegrityConstraintViolationException.class, EntityNotFoundException.class,  TransactionSystemException.class})
    public ResponseEntity<SimpleErrorResponse> handleDatabaseException(Exception ex) {
        log.error("BFF-MANAGER-CUSTOMER_EXCEPTION-BASE-DATOS:", ex);
        return new ResponseEntity<>(SimpleErrorResponse.builder()
            .code(CommonErrorEnum.DATABASE.getCode())
            .message("Error interno en la base de datos")
            .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Excepcion para una simple excepcion
     *
     * @param ex {@link SimpleException}
     * @return {@link  SimpleErrorResponse}
     */
    @ExceptionHandler(value = { SimpleException.class })
    ResponseEntity<SimpleErrorResponse> handleSimpleException(SimpleException ex) {
        return new ResponseEntity<>(SimpleErrorResponse.builder()
            .code(ex.getCode())
            .message(ex.getMessage())
            .build(), HttpStatus.valueOf(ex.getStatus()));
    }

    /**
     * Error de enums no validos
     *
     * @param ex {@link MethodArgumentTypeMismatchException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType().isEnum()) {
            String validValues = Arrays.toString(ex.getRequiredType().getEnumConstants());
            return new ResponseEntity<>(
                "Valor no válido para el tipo enum: " + ex.getValue() + ". Los valores válidos son: " + validValues,
                HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.badRequest().body("Error en el valor del parámetro: " + ex.getName());
    }

    /**
     * Mapea todos los errores de los request
     *
     * @param ex {@link MethodArgumentNotValidException}
     * @return {@link ResponseEntity}
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
        final HttpHeaders headers, final HttpStatusCode status, final WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        for (ObjectError error : allErrors) {
            String fieldName = error instanceof FieldError ? ((FieldError) error).getField() : error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        }

        return new ResponseEntity<>(SimpleErrorResponse.builder()
            .code(CommonErrorEnum.ERROR_DATA_REQUEST.getCode())
            .message(CommonErrorEnum.ERROR_DATA_REQUEST.getMessage())
            .errors(errors)
            .build(), HttpStatus.BAD_REQUEST);
    }

}
