package org.lshh.skeleton.core.resource.resourcer.dto;

import lombok.Getter;
import org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType;

@Getter
public class ResourcerUpdateCommand {
    private Long id;
    private String name;
    private String url;
    private String description;
    private ResourcerType type;
    private String username;
    private String password;
    private String adaptorName;

    public static ResourcerUpdateCommand of(Long id, String name, String url, String description, ResourcerType type, String username, String password, String adaptorName) {
        ResourcerUpdateCommand command = new ResourcerUpdateCommand();
        command.id = id;
        command.name = name;
        command.url = url;
        command.description = description;
        command.type = type;
        command.username = username;
        command.password = password;
        command.adaptorName = adaptorName;
        return command;
    }
}
