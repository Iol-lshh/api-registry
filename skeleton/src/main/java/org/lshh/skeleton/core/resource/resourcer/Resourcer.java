package org.lshh.skeleton.core.resource.resourcer;

public interface Resourcer {

    ResourcerType getType();

    Long getId();
    String getName();

    enum ResourcerType{
        RDBMS, API
    }
}
