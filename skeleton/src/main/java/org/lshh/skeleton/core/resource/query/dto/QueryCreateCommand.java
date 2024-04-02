package org.lshh.skeleton.core.resource.query.dto;

import lombok.Getter;

@Getter
public class QueryCreateCommand {

    String title;
    String body;
    Long resourcerId;

    public static QueryCreateCommand of(String title, String body, Long resourcerId) {
        QueryCreateCommand command = new QueryCreateCommand();
        command.title = title;
        command.body = body;
        command.resourcerId = resourcerId;
        return command;
    }
}
