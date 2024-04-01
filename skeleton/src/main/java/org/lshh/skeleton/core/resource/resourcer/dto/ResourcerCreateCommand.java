package org.lshh.skeleton.core.resource.resourcer.dto;

import lombok.Getter;
import org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType;

@Getter
public class ResourcerCreateCommand {
    private String name;
    private String url;
    private String description;
    private ResourcerType type;
}
