package org.lshh.skeleton.core.resource.query.dto;

import lombok.Getter;

@Getter
public class QueryUpdateCommand {
    Long id;
    String title;
    String body;

    Long resourcerId;

    public static QueryUpdateCommand of(Long id, String title, String body, Long resourcerId) {
        QueryUpdateCommand command = new QueryUpdateCommand();
        command.id = id;
        command.title = title;
        command.body = body;
        command.resourcerId = resourcerId;
        return command;
    }
}
