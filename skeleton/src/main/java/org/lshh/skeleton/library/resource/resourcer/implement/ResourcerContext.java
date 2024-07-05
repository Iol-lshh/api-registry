package org.lshh.skeleton.library.resource.resourcer.implement;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import org.lshh.skeleton.library.resource.resourcer.Resourcer;
import org.lshh.skeleton.library.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.library.resource.resourcer.dto.ResourcerUpdateCommand;

@Getter
@Entity
public class ResourcerContext {
    @Id
    private Long id;
    private String name;
    private String endpoint;
    private String description;
    private Resourcer.ResourcerType type;
    private String userName;
    private String password;


    public static ResourcerContext of(ResourcerCreateCommand command) {
        ResourcerContext context = new ResourcerContext();
        context.name = command.getName();
        context.endpoint = command.getEndpoint();
        context.description = command.getDescription();
        context.type = command.getType();
        return context;
    }
    public static ResourcerContext of(ResourcerUpdateCommand command) {
        ResourcerContext context = new ResourcerContext();
        context.id = command.getId();
        context.name = command.getName();
        context.endpoint = command.getEndpoint();
        context.description = command.getDescription();
        context.type = command.getType();
        return context;
    }
}
