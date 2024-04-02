package org.lshh.skeleton.core.resource.resourcer.implement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerCreateCommand;
import org.lshh.skeleton.core.resource.resourcer.dto.ResourcerUpdateCommand;

@Getter
@Entity
public class ResourcerContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    private String description;
    private ResourcerType type;
    private String username;
    private String password;
    private String adaptorName;

    public static ResourcerContext of(ResourcerCreateCommand command) {
        ResourcerContext context = new ResourcerContext();
        context.name = command.getName();
        context.url = command.getUrl();
        context.description = command.getDescription();
        context.type = command.getType();
        context.username = command.getUsername();
        context.password = command.getPassword();
        context.adaptorName = command.getAdaptorName();
        return context;
    }
    public static ResourcerContext of(ResourcerUpdateCommand command) {
        ResourcerContext context = new ResourcerContext();
        context.id = command.getId();
        context.name = command.getName();
        context.url = command.getUrl();
        context.description = command.getDescription();
        context.type = command.getType();
        context.username = command.getUsername();
        context.password = command.getPassword();
        context.adaptorName = command.getAdaptorName();
        return context;
    }

    public ResourcerContext setId(Long resourceId) {
        this.id = resourceId;
        return this;
    }
}
