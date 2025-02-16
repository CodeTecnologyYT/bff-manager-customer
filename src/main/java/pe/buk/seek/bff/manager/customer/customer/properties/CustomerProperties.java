/*
 * @(#)CustomerProperties.java
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
package pe.buk.seek.bff.manager.customer.customer.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * CustomerProperties.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 15-02-2025
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties("bff.manager.customer.calculate")
public class CustomerProperties {

    /** estimatedDeathYear. */
    private Integer estimatedDeathYear;
    /** estimatedWithDrawalYear. */
    private Integer estimatedWithDrawalYear;

}
