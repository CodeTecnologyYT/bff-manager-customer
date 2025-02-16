/*
 * @(#)RedisConfig.java
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

import java.time.Duration;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import pe.buk.seek.bff.manager.customer.shared.properties.RedisProperty;

/**
 * RedisConfig.
 *
 * @author Bryan Rosas.
 * @version 1.0.0, 16-02-2025
 */
@Configuration
@RequiredArgsConstructor
public class RedisConfig {

    /** props. */
    private final RedisProperty props;

    // -------------------------------------------------------------------
    // -- Métodos Públicos -----------------------------------------------
    // -------------------------------------------------------------------
    /**
     * Configuracion de Redis
     *
     * @param redisConnectionFactory {@link RedisConnectionFactory}
     * @return {@link CacheManager}
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        final var redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMillis(this.props.getTimeToLive()))
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(
                new RedisSerializerWithPrefix(this.props.getPrefix())));
        return RedisCacheManager.builder(redisConnectionFactory)
            .cacheDefaults(redisCacheConfiguration)
            .build();
    }

    /**
     * RedisSerializerWithPrefix.
     *
     * @author Bryan Rosas.
     * @version 1.0.0, 16-02-2025
     */
    @Getter
    @Setter
    @AllArgsConstructor
    static class RedisSerializerWithPrefix extends StringRedisSerializer {

        /** prefix. */
        private final String prefix;

        // -------------------------------------------------------------------
        // -- Métodos Públicos -----------------------------------------------
        // -------------------------------------------------------------------
        /**
         * {@inheritDoc}
         */
        @Override
        public byte[] serialize(String key) {
            return super.serialize(prefix + ":" + Objects.requireNonNull(key));
        }
    }


}
