package org.lshh.skeleton.core.resource.resourcer;

public interface RestApiResourcer extends Resourcer{
    @Override
    default Resourcer.ResourcerType getType(){
        return Resourcer.ResourcerType.API;
    }

    Integer healthCheck();
}
