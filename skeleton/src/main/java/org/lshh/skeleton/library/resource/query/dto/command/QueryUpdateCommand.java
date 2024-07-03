package org.lshh.skeleton.library.resource.query.dto.command;

import lombok.Getter;

@Getter
public class QueryUpdateCommand {
    Long id;
    String title;
    String body;

    Long resourceId;

    public static QueryUpdateCommand of(Long id, String title, String body, Long resourceId) {
        QueryUpdateCommand command = new QueryUpdateCommand();
        command.id = id;
        command.title = title;
        command.body = body;
        command.resourceId = resourceId;
        return command;
    }
}
