package com.aston.frontendpracticeservice.integration;

import com.aston.frontendpracticeservice.integration.annotation.IT;
import com.redis.testcontainers.RedisContainer;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

@IT
@Sql({
        "classpath:sql/mock_data_users_and_roles.sql",
        "classpath:sql/mock_data_requisites.sql"
})
public abstract class IntegrationTestBase {

    private static final PostgreSQLContainer<?> psqlContainer = new PostgreSQLContainer<>("postgres:14");

    private static final RedisContainer redisContainer = new RedisContainer("redis:8.2.0");

    @BeforeAll
    static void runContainer() {
        psqlContainer.start();
        redisContainer.start();
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry. add("spring.datasource.url", psqlContainer::getJdbcUrl);
        registry.add("spring.data.redis.port", redisContainer::getRedisPort);
        registry.add("spring.data.redis.host", redisContainer::getRedisHost);
    }
}
