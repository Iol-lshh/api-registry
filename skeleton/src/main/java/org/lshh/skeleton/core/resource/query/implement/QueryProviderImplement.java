package org.lshh.skeleton.core.resource.query.implement;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.resource.query.QueryProvider;
import org.lshh.skeleton.core.resource.query.QueryRepository;
import org.lshh.skeleton.core.resource.query.dto.command.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.command.QueryUpdateCommand;
import org.lshh.skeleton.core.resource.query.exception.QueryTaskException;
import org.lshh.skeleton.core.resource.resource.Resource;
import org.lshh.skeleton.core.resource.resource.ResourceManager;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class QueryProviderImplement implements QueryProvider {

    private final QueryRepository repository;
    private final ResourceManager resourceManager;

    @Override
    public Optional<Query> find(Long id) {
        Optional<QueryContext> optionalQueryContext = repository.findById(id);

        if (
            optionalQueryContext.isPresent()
            && optionalQueryContext.get() instanceof QueryContext queryContext
        ) {
            if(resourceManager.isExist(queryContext.getResourceId())){
                throw new QueryTaskException("Resource not found");
            }
            Resource resource = resourceManager.find(queryContext.getResourceId());
            return Optional.of(Query.of(queryContext, resource.getDataSource()));
        }

        throw new QueryTaskException("QueryTask not found");
    }

    @Override
    public Query create(QueryCreateCommand command) {
        QueryContext newOne = QueryContext.of(command);

        if(resourceManager.isExist(newOne.getResourceId())){
            throw new QueryTaskException("Resource not found");
        }
        Resource resource = resourceManager.find(newOne.getResourceId());

        return Query.of(repository.create(newOne), resource.getDataSource());
    }

    @Override
    public Query update(QueryUpdateCommand command) {
        QueryContext renewal = QueryContext.of(command);

        if(resourceManager.isExist(renewal.getResourceId())){
            throw new QueryTaskException("Resource not found");
        }
        Resource resource = resourceManager.find(renewal.getResourceId());

        return Query.of(repository.update(renewal), resource.getDataSource());
    }
}
