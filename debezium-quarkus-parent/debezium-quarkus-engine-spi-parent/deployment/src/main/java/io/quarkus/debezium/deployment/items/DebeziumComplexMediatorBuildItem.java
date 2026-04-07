/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */

package io.quarkus.debezium.deployment.items;

import io.quarkus.arc.processor.BeanInfo;
import io.quarkus.builder.item.MultiBuildItem;
import org.jboss.jandex.DotName;
import org.jboss.jandex.MethodInfo;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Represents a class annotated with a primary method like {@code Capturing} and secondary like {@code TombstoneSupport}
 */
public final class DebeziumComplexMediatorBuildItem extends MultiBuildItem {

    private final BeanInfo bean;
    private final MethodInfo primaryMethodInfo;
    private final DotName primaryDotName;
    private final Map<DotName, MethodInfo> secondaryMethodInfo;

    public DebeziumComplexMediatorBuildItem(BeanInfo bean,
                                            MethodInfo primaryMethodInfo,
                                            DotName primaryDotName,
                                            Map<DotName, MethodInfo> secondaryMethodInfo) {
        this.bean = bean;
        this.primaryMethodInfo = primaryMethodInfo;
        this.primaryDotName = primaryDotName;
        this.secondaryMethodInfo = secondaryMethodInfo;
    }

    public BeanInfo getBean() {
        return bean;
    }

    public MethodInfo getPrimaryMethodInfo() {
        return primaryMethodInfo;
    }

    public DotName getPrimaryDotName() {
        return primaryDotName;
    }

    public Optional<MethodInfo> secondaryMethodInfo(DotName dotName) {
        return Optional.ofNullable(secondaryMethodInfo.get(dotName));
    }

}
