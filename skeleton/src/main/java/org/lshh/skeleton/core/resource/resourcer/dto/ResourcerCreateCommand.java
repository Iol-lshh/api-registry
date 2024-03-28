package org.lshh.skeleton.core.resource.resourcer.dto;

import org.lshh.skeleton.core.resource.resourcer.Resourcer;

public class ResourcerCreateCommand {
    private Long id;
    private String name;
    private String endpoint;
    private String description;
    private Resourcer.ResourcerType type;
}
