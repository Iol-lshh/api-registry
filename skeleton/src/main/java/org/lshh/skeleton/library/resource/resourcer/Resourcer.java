package org.lshh.skeleton.library.resource.resourcer;

public interface Resourcer {

    ResourcerType getType();

    Long getId();

    boolean isReady();

    String getName();

    enum ResourcerType{
        RDBMS, API
    }
}
