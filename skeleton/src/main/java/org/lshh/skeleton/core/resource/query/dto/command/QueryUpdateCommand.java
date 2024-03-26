package org.lshh.skeleton.core.resource.query.dto.command;

import lombok.Getter;

@Getter
public class QueryUpdateCommand {
    Long id;
    String title;
    String body;

    public static QueryUpdateCommand of(Long id, String title, String body) {
        QueryUpdateCommand command = new QueryUpdateCommand();
        command.id = id;
        command.title = title;
        command.body = body;
        return command;
    }
}
