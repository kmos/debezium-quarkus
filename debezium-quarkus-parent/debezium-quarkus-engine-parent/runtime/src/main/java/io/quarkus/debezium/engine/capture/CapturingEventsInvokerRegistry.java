/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.quarkus.debezium.engine.capture;

import java.util.List;

public interface CapturingEventsInvokerRegistry<T> {
    CapturingInvoker<T> get(T identifier);

    List<CapturingInvoker<T>> invokers();
}
