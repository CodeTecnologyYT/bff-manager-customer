/*
 * @(#)CustomerMetricsService.java
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
package pe.buk.seek.bff.manager.customer.customer.metrics;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.buk.seek.bff.manager.customer.customer.entities.CustomerEntity;
import pe.buk.seek.bff.manager.customer.customer.repositories.CustomerRepository;

/**
 * CustomerMetricsService.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 15-02-2025
 */
@Service
@Getter
@RequiredArgsConstructor
public class CustomerMetricsService {

    /** customerRepository. */
    private final CustomerRepository customerRepository;
    /** meterRegistry. */
    private final MeterRegistry meterRegistry;
    /** averageAge. */
    private Double averageAge = 0.0;
    /** deviationAge. */
    private Double deviationAge = 0.0;

    // -------------------------------------------------------------------
    // -- Métodos Publicos  ----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Crea las metricas y inicializa las metricas
     */
    @PostConstruct
    public void registerMetrics() {
        //crea las metricas
        Gauge.builder("customer.age.average", this, CustomerMetricsService::getAverageAge)
            .description("Promedio de edad de los clientes")
            .register(meterRegistry);
        Gauge.builder("customer.age.deviation", this, CustomerMetricsService::getDeviationAge)
            .description("Desviación estándar de la edad de los clientes")
            .register(meterRegistry);
        // inicializa los metricas
        this.calculateStatistics();
    }

    /**
     * Calcula y asigna los valores las metricas de promedio y desviacion de edades
     */
    public void calculateStatistics() {
        final var ages = this.customerRepository.findAll().stream()
            .map(CustomerEntity::getAge)
            .toList();
        if (ages.isEmpty()) {
            averageAge = 0.0;
            deviationAge = 0.0;
            return;
        }
        // calcula el promedio de edades de los clientes
        final var  statistics = ages.stream().mapToDouble(a -> a).summaryStatistics();
        averageAge = statistics.getAverage();
        // calcula la desviacion estandar de las edades de los clientes
        double variance = ages.stream().mapToDouble(a -> Math.pow(a - averageAge, 2)).sum() / ages.size();
        deviationAge = Math.sqrt(variance);
    }

}
