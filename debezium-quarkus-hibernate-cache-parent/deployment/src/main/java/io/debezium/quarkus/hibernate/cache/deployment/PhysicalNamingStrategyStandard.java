/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.debezium.quarkus.hibernate.cache.deployment;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;

public class PhysicalNamingStrategyStandard {

    private final PhysicalNamingStrategyStandardImpl strategy = new PhysicalNamingStrategyStandardImpl();

    public PhysicalNamingStrategyStandard() {
    }

    public String apply(String value) {
        return strategy.toPhysicalTableName(Identifier.toIdentifier(value), null).getCanonicalName();
    }
}