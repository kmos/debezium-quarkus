/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.quarkus.debezium.deployment.engine;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.given;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import io.debezium.runtime.Capturing;
import io.debezium.runtime.CapturingEvent;
import io.quarkus.maven.dependency.Dependency;
import io.quarkus.test.QuarkusUnitTest;
import io.quarkus.test.common.QuarkusTestResource;

@QuarkusTestResource(PostgresResource.class)
public class CompatibilityModeTest {

    @Inject
    CaptureProductsHandler captureProductsHandler;

    @RegisterExtension
    static final QuarkusUnitTest setup = new QuarkusUnitTest()
            .setForcedDependencies(List.of(Dependency.of("io.debezium", "debezium-connector-postgres")))
            .withApplicationRoot((jar) -> jar.addClasses(CaptureProductsHandler.class))
            .withConfigurationResource("debezium-quarkus.properties");

    @Test
    @DisplayName("should invoke the capture handler in compatible mode")
    void shouldInvokeDefaultCapture() {
        given().await()
                .atMost(10, TimeUnit.SECONDS)
                .untilAsserted(() -> assertThat(captureProductsHandler.isInvoked()).isTrue());

    }

    @ApplicationScoped
    static class CaptureProductsHandler {
        private final AtomicBoolean isInvoked = new AtomicBoolean(false);

        @Capturing()
        public void newCapture(CapturingEvent<SourceRecord, SourceRecord> event) {
            isInvoked.set(true);
        }

        public boolean isInvoked() {
            return isInvoked.getAndSet(false);
        }

    }
}
