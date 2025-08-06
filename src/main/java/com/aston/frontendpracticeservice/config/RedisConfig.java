package com.aston.frontendpracticeservice.config;

import com.aston.frontendpracticeservice.config.properties.RedisCacheProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collections;
import java.util.Map;

import static org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;

@Configuration
@EnableCaching
@EnableConfigurationProperties(RedisCacheProperties.class)
public class RedisConfig {

    private final StringRedisSerializer keySerializer;

    private final GenericJackson2JsonRedisSerializer valueSerializer;

    private final RedisCacheProperties redisCacheProperties;

    public RedisConfig(@Qualifier("redisObjectMapper") ObjectMapper objectMapper, RedisCacheProperties redisCacheProperties) {
        this.keySerializer = new StringRedisSerializer();
        this.valueSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);
        this.redisCacheProperties = redisCacheProperties;
    }

    @Bean
    @Primary
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(defaultConfiguration())
                .transactionAware()
                .withInitialCacheConfigurations(cachesConfiguration())
                .build();
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(keySerializer);
        redisTemplate.setValueSerializer(valueSerializer);
        redisTemplate.setEnableTransactionSupport(true);

        return redisTemplate;
    }

    private RedisCacheConfiguration defaultConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(SerializationPair.fromSerializer(keySerializer))
                .serializeValuesWith(SerializationPair.fromSerializer(valueSerializer))
                .disableCachingNullValues();
    }

    private Map<String, RedisCacheConfiguration> cachesConfiguration() {
        return Collections.singletonMap(
                redisCacheProperties.getUsersCacheName(),
                RedisCacheConfiguration.defaultCacheConfig()
                        .serializeKeysWith(SerializationPair.fromSerializer(keySerializer))
                        .serializeValuesWith(SerializationPair.fromSerializer(valueSerializer))
                        .disableCachingNullValues()
                        .entryTtl(redisCacheProperties.getUsersTtl())
        );
    }
}
