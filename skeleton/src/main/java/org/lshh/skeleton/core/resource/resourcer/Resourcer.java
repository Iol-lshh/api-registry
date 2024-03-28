package org.lshh.skeleton.core.resource.resourcer;

public interface Resourcer {

    ResourcerType getType();

    Long getId();

    enum ResourcerType{
        RDBMS, API
    }
}
