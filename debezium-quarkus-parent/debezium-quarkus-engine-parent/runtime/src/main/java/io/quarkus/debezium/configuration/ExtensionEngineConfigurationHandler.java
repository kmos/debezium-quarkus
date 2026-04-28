/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.quarkus.debezium.configuration;

import java.lang.annotation.Annotation;
import java.util.Iterator;

import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.util.TypeLiteral;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import io.debezium.runtime.configuration.DebeziumEngineConfiguration;
import io.debezium.runtime.configuration.DebeziumEngineConfigurationHandler;
import io.debezium.runtime.configuration.EngineConfigurationOverride;
import io.debezium.runtime.configuration.ExtensionDebeziumEngineConfiguration;
import io.smallrye.config.Config;

@Singleton
public class ExtensionEngineConfigurationHandler implements DebeziumEngineConfigurationHandler {
    private final Instance<EngineConfigurationOverride> overrideInstance;

    @Inject
    public ExtensionEngineConfigurationHandler(Instance<EngineConfigurationOverride> overrideInstance) {
        this.overrideInstance = overrideInstance;
    }

    @Override
    public DebeziumEngineConfiguration get() {
        if (overrideInstance.isResolvable()) {
            return Config.getOrCreate().getConfigMapping(overrideInstance.get().get());
        }

        return Config.getOrCreate().getConfigMapping(ExtensionDebeziumEngineConfiguration.class);
    }

    public static ExtensionEngineConfigurationHandler createForBuildTime() {
        return new ExtensionEngineConfigurationHandler(new Instance<>() {
            @Override
            public Instance<EngineConfigurationOverride> select(Annotation... qualifiers) {
                return null;
            }

            @Override
            public <U extends EngineConfigurationOverride> Instance<U> select(Class<U> subtype, Annotation... qualifiers) {
                return null;
            }

            @Override
            public <U extends EngineConfigurationOverride> Instance<U> select(TypeLiteral<U> subtype, Annotation... qualifiers) {
                return null;
            }

            @Override
            public boolean isUnsatisfied() {
                return true;
            }

            @Override
            public boolean isAmbiguous() {
                return true;
            }

            @Override
            public void destroy(EngineConfigurationOverride instance) {

            }

            @Override
            public Handle<EngineConfigurationOverride> getHandle() {
                return null;
            }

            @Override
            public Iterable<? extends Handle<EngineConfigurationOverride>> handles() {
                return null;
            }

            @Override
            public EngineConfigurationOverride get() {
                return null;
            }

            @Override
            public Iterator<EngineConfigurationOverride> iterator() {
                return null;
            }
        });
    }
}
