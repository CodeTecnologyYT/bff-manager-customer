/*
 * @(#)SwaggerProperty.java
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
package pe.buk.seek.bff.manager.customer.shared.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerProperty.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 14-02-2025
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Configuration
@ConfigurationProperties("swagger.info")
public class SwaggerProperty {

    /** name. */
    private String name;
    /** description. */
    private String description;
    /** version. */
    private String version;
    /** contact. */
    private Contact contact;

    /**
     * Contact.
     *
     * @author Bryan Rosas.
     * @version 1.0.0, 14-02-2025
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Contact {

        /** name. */
        private String name;
        /** email. */
        private  String email;

    }

}
