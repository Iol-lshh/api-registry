package org.lshh.skeleton.library.resource.query.dto.command;

import lombok.Getter;

@Getter
public class QueryCreateCommand {

    String title;
    String body;
    Long resourceId;

    public static QueryCreateCommand of(String title, String body, Long resourceId) {
        QueryCreateCommand command = new QueryCreateCommand();
        command.title = title;
        command.body = body;
        command.resourceId = resourceId;
        return command;
    }
}
