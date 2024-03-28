package org.lshh.skeleton.core.resource.resourcer;

import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerUpdateCommand;
import org.lshh.skeleton.core.resource.resourcer.implement.ResourcerContext;
import org.lshh.skeleton.core.resource.resourcer.implement.SimpleRdbmsResourcer;

import java.util.List;
import java.util.Optional;

public interface ResourcerProvider {
    Optional<Resourcer> find(Long resourceId);

    List<Resourcer> findAll();

    Resourcer create(ResourcerCreateCommand command);

    Resourcer update(ResourcerUpdateCommand command);

    class Resourcers{
        public static Resourcer of(ResourcerContext context){
            switch(context.getType()){
                case RDBMS:
                    return SimpleRdbmsResourcer.of(context);

                default:
                    return null;
            }
        }
    }
}
