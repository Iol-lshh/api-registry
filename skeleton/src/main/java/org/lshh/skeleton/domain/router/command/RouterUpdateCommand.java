package org.lshh.skeleton.domain.router.command;

import lombok.Getter;

@Getter
public class RouterUpdateCommand {
    Long id;
    String name;
    String path;
    String description;
    Long taskId;

    public static RouterUpdateCommand of(Long id, String name, String path, String description, Long taskId) {
        RouterUpdateCommand command = new RouterUpdateCommand();
        command.id = id;
        command.name = name;
        command.path = path;
        command.description = description;
        command.taskId = taskId;
        return command;
    }
}
