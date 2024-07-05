package org.lshh.skeleton.library.resource.query.implement;

import org.lshh.skeleton.library.resource.query.Query;
import org.lshh.skeleton.library.resource.query.QueryProvider;
import org.lshh.skeleton.library.resource.query.QueryRepository;
import org.lshh.skeleton.library.resource.query.dto.command.QueryCreateCommand;
import org.lshh.skeleton.library.resource.query.dto.command.QueryUpdateCommand;
import org.lshh.skeleton.library.resource.query.exception.QueryTaskException;

import javax.sql.DataSource;
import java.util.Optional;

public class QueryProviderImplement implements QueryProvider {

    private final QueryRepository repository;

    private QueryProviderImplement(QueryRepository repository) {
        this.repository = repository;
    }
    public static QueryProvider of(QueryRepository repository) {
        return new QueryProviderImplement(repository);
    }

    @Override
    public Optional<Query> find(Long id, DataSource dataSource) {
        Optional<QueryContext> optionalQueryContext = repository.findById(id);

        if (
            optionalQueryContext.isPresent()
            && optionalQueryContext.get() instanceof QueryContext queryContext
        ) {
            JdbcTemplateWrapper jdbcTemplate = JdbcTemplateWrapper.of(dataSource);
            return Optional.of(Query.of(queryContext, jdbcTemplate));
        }

        throw new QueryTaskException("QueryTask not found");
    }

    @Override
    public Query create(QueryCreateCommand command, DataSource dataSource) {
        QueryContext newOne = QueryContext.of(command);

        JdbcTemplateWrapper jdbcTemplate = JdbcTemplateWrapper.of(dataSource);
        return Query.of(repository.create(newOne), jdbcTemplate);
    }

    @Override
    public Query update(QueryUpdateCommand command, DataSource dataSource) {
        QueryContext renewal = QueryContext.of(command);
        JdbcTemplateWrapper jdbcTemplate = JdbcTemplateWrapper.of(dataSource);
        return Query.of(repository.update(renewal), jdbcTemplate);
    }
}
