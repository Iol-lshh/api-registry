package org.lshh.skeleton.core.resource.resourcer.implement;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.lshh.skeleton.core.resource.resourcer.Resourcer.ResourcerType;

@Entity
public class ResourcerContext {
    @Id
    private Long id;
    private String name;
    private String endpoint;
    private String description;
    private ResourcerType type;
}
