/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.debezium.quarkus.hibernate.cache;

/**
 * Defines the eviction strategy to apply to an invalidation event
 *
 *  @author Giovanni Panice
 */
public interface DebeziumEvictionStrategy {
    void evict(InvalidationEvent event);
}
