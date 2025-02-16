/*
 * @(#)ExecutorConfig.java
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

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * ExecutorConfig.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 15-02-2025
 */
@Configuration
public class ExecutorConfig {

    // -------------------------------------------------------------------
    // -- Beans Instanciados  --------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Configuracion del Executor.
     *
     * @return {@link ExecutorService}
     */
    @Bean
    public ExecutorService executorService() {
        return Executors.newFixedThreadPool(4);
    }

}
