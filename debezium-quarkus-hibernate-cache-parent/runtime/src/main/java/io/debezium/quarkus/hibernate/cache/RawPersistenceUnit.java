/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.debezium.quarkus.hibernate.cache;

import java.util.List;
import java.util.Optional;

public record RawPersistenceUnit(
        String name,
        List<RawJpaInfo> rawJpaInfo,
        CacheMode mode) {

    public record RawJpaInfo(
            String name,
            String table,
            Optional<String> hibernateId,
            Optional<String> hibernateIdType,
            boolean cached,
            String persistentUnit,
            String fqcn) {
    }
}
