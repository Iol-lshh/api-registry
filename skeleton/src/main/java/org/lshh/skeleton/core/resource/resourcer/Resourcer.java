package org.lshh.skeleton.core.resource.resourcer;

public interface Resourcer {

    ResourcerType getType();

    Long getId();
    String getName();

    default <K extends Resourcer> K as(Class<K> type){
        if(type.isInstance(this)){
            return type.cast(this);
        }else{
            throw new IllegalArgumentException("Resourcer type is not matched");
        }
    }

    enum ResourcerType{
        RDBMS, API
    }
}
