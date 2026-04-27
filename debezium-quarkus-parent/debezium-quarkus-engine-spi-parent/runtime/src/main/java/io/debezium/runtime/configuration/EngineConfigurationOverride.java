/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.debezium.runtime.configuration;

/**
 * Provides a mechanism to override the default Debezium engine configuration class.
 * <p>
 * This interface allows extensions to supply a custom {@link DebeziumEngineConfiguration}
 * implementation class that should be used instead of the default
 * {@link io.debezium.runtime.configuration.ExtensionDebeziumEngineConfiguration}.
 * <p>
 * Implementations of this interface should be registered as CDI beans (typically
 * {@code @Singleton}) and will be automatically detected by the
 * {@link io.debezium.runtime.configuration.DebeziumEngineConfigurationHandler} at runtime.
 * If multiple implementations are present, CDI resolution rules apply.
 * <p>
 * <b>Usage Example:</b>
 * <pre>
 * {@code @Singleton}
 * public class CustomConfigurationOverride implements EngineConfigurationOverride {
 *     {@code @Override}
 *     public Class&lt;? extends DebeziumEngineConfiguration&gt; get() {
 *         return CustomDebeziumConfiguration.class;
 *     }
 * }
 * </pre>
 *
 * @see io.debezium.runtime.configuration.DebeziumEngineConfiguration
 * @see io.debezium.runtime.configuration.DebeziumEngineConfigurationHandler
 */
public interface EngineConfigurationOverride {

    /**
     * Returns the configuration class that should be used for Debezium engine configuration.
     * <p>
     * The returned class must be a SmallRye Config {@code @ConfigMapping} interface that
     * extends {@link DebeziumEngineConfiguration} and will be instantiated by the
     * Quarkus configuration system.
     *
     * @return the configuration class to use, never {@code null}
     */
    Class<? extends DebeziumEngineConfiguration> get();
}
