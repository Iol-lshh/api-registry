package org.lshh.skeleton.core.resource.query.implement;

import jakarta.persistence.*;
import lombok.Getter;
import org.lshh.skeleton.core.resource.query.dto.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.QueryUpdateCommand;

@Getter
@Entity
@Table(name = "query")
public class QueryContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String title;
    String body;
    Long resourcerId;

    public static QueryContext of(QueryCreateCommand command) {
        QueryContext context = new QueryContext();
        context.title = command.getTitle();
        context.body = command.getBody();
        context.resourcerId = command.getResourcerId();
        return context;
    }

    public static QueryContext of(QueryUpdateCommand command) {
        QueryContext context = new QueryContext();
        context.id = command.getId();
        context.title = command.getTitle();
        context.body = command.getBody();
        context.resourcerId = command.getResourcerId();
        return context;
    }

    public QueryContext setId(Long id) {
        this.id = id;
        return this;
    }
}
