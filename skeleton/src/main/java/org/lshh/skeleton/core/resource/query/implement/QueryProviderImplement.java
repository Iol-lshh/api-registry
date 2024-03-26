package org.lshh.skeleton.core.resource.query.implement;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.resource.query.QueryProvider;
import org.lshh.skeleton.core.resource.query.QueryRepository;
import org.lshh.skeleton.core.resource.query.QueryTask;
import org.lshh.skeleton.core.resource.query.dto.command.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.command.QueryUpdateCommand;
import org.lshh.skeleton.core.resource.query.exception.QueryTaskException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class QueryProviderImplement implements QueryProvider {
    private final QueryRepository repository;


    @Override
    public QueryTask find(Long id) {
        Optional<QueryContext> optionalQueryTask = repository.findById(id);
        if(optionalQueryTask.isPresent()){
            return QueryTask.of(optionalQueryTask.get());
        }else{
            throw new QueryTaskException("QueryTask not found");
        }
    }

    @Override
    public QueryTask create(QueryCreateCommand command) {
        QueryContext newOne = QueryContext.of(command);
        return QueryTask.of(repository.create(newOne));
    }

    @Override
    public QueryTask update(QueryUpdateCommand command) {
        QueryContext renewal = QueryContext.of(command);
        return QueryTask.of(repository.update(renewal));
    }
}
