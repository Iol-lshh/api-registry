package org.lshh.skeleton.core.resource.query.dto.command;

import lombok.Getter;

@Getter
public class QueryCreateCommand {

    String title;
    String body;

    public static QueryCreateCommand of(String title, String body) {
        QueryCreateCommand command = new QueryCreateCommand();
        command.title = title;
        command.body = body;
        return command;
    }
}
