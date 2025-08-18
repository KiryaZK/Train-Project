package com.aston.frontendpracticeservice.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.caches")
public class RedisCacheProperties {

    private String usersCacheName;

    private Duration usersTtl;
}
