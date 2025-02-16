/*
 * @(#)RedisProperties.java
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

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.stereotype.Component;

/**
 * RedisProperties.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 16-02-2025
 */
@Data
@Component
@NoArgsConstructor
@ConfigurationProperties("spring.redis")
public class RedisProperty {

    /** prefix. */
    private String prefix;
    /** timeToLive. */
    private Long timeToLive;

}
