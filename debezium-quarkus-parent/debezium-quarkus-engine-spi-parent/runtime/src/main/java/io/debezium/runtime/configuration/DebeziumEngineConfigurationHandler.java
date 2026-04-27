/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.debezium.runtime.configuration;

/**
 * Handler interface for providing access to Debezium engine configuration.
 * <p>
 * This interface defines a contract for retrieving the {@link DebeziumEngineConfiguration}
 * instance that should be used by the Debezium engine at runtime. Implementations of this
 * interface can provide custom logic for configuration resolution, including support for
 * configuration overrides.
 * <p>
 * The primary implementation {@code ExtensionEngineConfigurationHandler} checks for the
 * presence of an {@link EngineConfigurationOverride}
 * and uses it if available, otherwise defaults to the standard
 * {@link ExtensionDebeziumEngineConfiguration}.
 *
 * @see DebeziumEngineConfiguration
 * @see ExtensionDebeziumEngineConfiguration
 */
public interface DebeziumEngineConfigurationHandler {

    /**
     * Retrieves the Debezium engine configuration instance.
     * <p>
     * This method is called by the Debezium runtime to obtain the configuration
     * that should be used for initializing and running the Debezium engine.
     *
     * @return the Debezium engine configuration, never {@code null}
     */
    DebeziumEngineConfiguration get();
}
