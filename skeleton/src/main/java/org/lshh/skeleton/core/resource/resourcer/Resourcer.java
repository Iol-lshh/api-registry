package org.lshh.skeleton.core.resource.resourcer;

public interface Resourcer {

    ResourcerType getType();

    Long getId();
    String getName();

    default <K extends Resourcer> K as(Class<K> subClass){
        if(!subClass.isInstance(this)){
            throw new IllegalArgumentException("Task is not instance of " + subClass.getName());
        }
        return subClass.cast(this);
    }

    enum ResourcerType{
        RDBMS,
        API
    }
}
