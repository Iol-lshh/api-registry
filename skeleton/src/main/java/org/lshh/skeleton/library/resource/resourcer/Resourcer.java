package org.lshh.skeleton.library.resource.resourcer;

public interface Resourcer {

    ResourcerType getType();

    Long getId();

    enum ResourcerType{
        RDBMS, API
    }
}
