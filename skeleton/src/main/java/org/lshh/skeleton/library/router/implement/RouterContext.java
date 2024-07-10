package org.lshh.skeleton.library.router.implement;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.lshh.skeleton.library.router.dto.command.RouterCreateCommand;
import org.lshh.skeleton.library.router.dto.command.RouterUpdateCommand;

@Getter
@DynamicInsert
@DynamicUpdate
@Entity
public class RouterContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String path;
    String description;
    Long taskId;

    public RouterContext setId(Long id) {
        this.id = id;
        return this;
    }

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
