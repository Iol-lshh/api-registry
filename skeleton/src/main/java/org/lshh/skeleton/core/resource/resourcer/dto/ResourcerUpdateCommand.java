package org.lshh.skeleton.core.resource.resourcer.dto;

import lombok.Getter;
import org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType;

@Getter
public class ResourcerUpdateCommand {
    private Long id;
    private String name;
    private String endpoint;
    private String description;
    private ResourcerType type;
}
