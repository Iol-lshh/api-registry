package org.lshh.skeleton.domain.router.implement;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import org.lshh.skeleton.domain.router.command.RouterCreateCommand;
import org.lshh.skeleton.domain.router.command.RouterUpdateCommand;

@Getter
@Entity
@Table(name = "router")
public class RouterContext {
    @Id
    Long id;

    String name;
    String path;
    String description;
    Long taskId;

    public static RouterContext of(RouterCreateCommand command){
        RouterContext context = new RouterContext();
        context.name = command.getName();
        context.path = command.getPath();
        context.description = command.getDescription();
        context.taskId = command.getTaskId();
        return context;
    }

    public static RouterContext of(RouterUpdateCommand command){
        RouterContext context = new RouterContext();
        context.id = command.getId();
        context.name = command.getName();
        context.path = command.getPath();
        context.description = command.getDescription();
        context.taskId = command.getTaskId();
        return context;
    }
}
