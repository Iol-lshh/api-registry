package org.lshh.skeleton.library.resource.resourcer;

import org.lshh.skeleton.library.resource.resourcer.implement.SimpleRdbmsResourcer;
import org.lshh.skeleton.library.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.library.resource.resourcer.dto.ResourcerUpdateCommand;
import org.lshh.skeleton.library.resource.resourcer.implement.ResourcerContext;

import java.util.List;
import java.util.Optional;

public interface ResourcerProvider {
    Optional<Resourcer> find(Long resourceId);
    Optional<Resourcer> find(String resourceName);

    List<Resourcer> findAll();

    Resourcer create(ResourcerCreateCommand command);

    Resourcer update(ResourcerUpdateCommand command);

    class Resourcers{
        public static Resourcer of(ResourcerContext context){
            return switch (context.getType()) {
                case RDBMS -> SimpleRdbmsResourcer.of(context);
                default -> null;
            };
        }
    }
}
