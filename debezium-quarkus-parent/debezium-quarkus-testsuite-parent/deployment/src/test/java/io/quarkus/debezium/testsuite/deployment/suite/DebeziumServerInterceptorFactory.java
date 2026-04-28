/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.quarkus.debezium.testsuite.deployment.suite;

import io.smallrye.config.ConfigSourceInterceptor;
import io.smallrye.config.ConfigSourceInterceptorContext;
import io.smallrye.config.ConfigSourceInterceptorFactory;
import io.smallrye.config.FallbackConfigSourceInterceptor;

public class DebeziumServerInterceptorFactory implements ConfigSourceInterceptorFactory {
    private final String QUARKUS = "quarkus.datasource";
    private final String DEBEZIUM = "debezium.source.datasource";

    @Override
    public ConfigSourceInterceptor getInterceptor(ConfigSourceInterceptorContext context) {
        return new FallbackConfigSourceInterceptor(value -> {
            if (value.equals(QUARKUS + ".jdbc.url")) {
                return DEBEZIUM + ".jdbc.url";
            }

            if (value.equals(QUARKUS + ".username")) {
                return DEBEZIUM + ".username";
            }

            if (value.equals(QUARKUS + ".password")) {
                return DEBEZIUM + ".password";
            }
            return value;
        });
    }
}
