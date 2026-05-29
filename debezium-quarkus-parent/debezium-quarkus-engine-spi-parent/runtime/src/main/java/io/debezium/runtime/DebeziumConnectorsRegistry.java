/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.debezium.runtime;

import java.util.List;
import java.util.Optional;

public interface DebeziumConnectorsRegistry {
    /**
     *
     * @return the {@link DebeziumConnectorRegistry} for this {@link Connector}
     */
    Optional<DebeziumConnectorRegistry> registry(Connector connector);

    /**
     *
     * @param manifest
     * @return the {@link Debezium} engine instance assigned to a {@link EngineManifest}
     */
    Optional<Debezium> get(EngineManifest manifest);

    /**
     *
     * @return the {@link Debezium} engines inside the registry
     */
    List<Debezium> engines();
}
