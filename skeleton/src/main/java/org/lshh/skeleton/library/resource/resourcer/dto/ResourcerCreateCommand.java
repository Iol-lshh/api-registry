package org.lshh.skeleton.library.resource.resourcer.dto;

import lombok.Getter;
import org.lshh.skeleton.library.resource.resourcer.Resourcer;

@Getter
public class ResourcerCreateCommand {
    private String name;
    private String endpoint;
    private String description;
    private Resourcer.ResourcerType type;
}
