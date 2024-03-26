package org.lshh.skeleton.core.router.dto.command;

import lombok.Getter;

@Getter
public class RouterCreateCommand {
    String name;
    String path;
    String description;
    Long taskId;

    public static RouterCreateCommand of(String name, String path, String description, Long taskId) {
        RouterCreateCommand command = new RouterCreateCommand();
        command.name = name;
        command.path = path;
        command.description = description;
        command.taskId = taskId;
        return command;
    }
}
