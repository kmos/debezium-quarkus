/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.quarkus.debezium.deployment.engine;

import java.time.Duration;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.utility.DockerImageName;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class PostgresResource implements QuarkusTestResourceLifecycleManager {

    private static final String POSTGRES_IMAGE = "quay.io/debezium/postgres:15";

    private static final DockerImageName POSTGRES_DOCKER_IMAGE_NAME = DockerImageName.parse(POSTGRES_IMAGE)
            .asCompatibleSubstituteFor("postgres");

    private static final PostgreSQLContainer<?> POSTGRES_CONTAINER = new PostgreSQLContainer<>(POSTGRES_DOCKER_IMAGE_NAME)
            .waitingFor(Wait.forLogMessage(".*database system is ready to accept connections.*", 2))
            .withEnv("POSTGRES_INITDB_ARGS", "-E UTF8")
            .withEnv("LANG", "en_US.utf8")
            .withUsername("postgres")
            .withPassword("postgres")
            .withDatabaseName("postgres")
            .withInitScript("initialize-postgres-database.sql")
            .withStartupTimeout(Duration.ofSeconds(30));

    @Override
    public Map<String, String> start() {
        POSTGRES_CONTAINER.start();
        return Map.of(
                "quarkus.debezium.database.hostname", POSTGRES_CONTAINER.getHost(),
                "quarkus.debezium.database.port", POSTGRES_CONTAINER.getMappedPort(5432).toString(),
                "quarkus.debezium.database.user", POSTGRES_CONTAINER.getUsername(),
                "quarkus.debezium.database.password", POSTGRES_CONTAINER.getPassword(),
                "quarkus.debezium.database.dbname", POSTGRES_CONTAINER.getDatabaseName());
    }

    public void stop() {
        POSTGRES_CONTAINER.stop();
    }
}
