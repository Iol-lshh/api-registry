package org.lshh.skeleton.core.resource.query.implement;

import org.lshh.skeleton.core.resource.query.Query;
import org.lshh.skeleton.core.resource.query.QueryManager;
import org.lshh.skeleton.core.resource.query.QueryProvider;
import org.lshh.skeleton.core.resource.query.dto.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.QueryUpdateCommand;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QueryManagerImplement implements QueryManager {
    private final Map<Long, Query> cacheMap = new HashMap<>();
    private final QueryProvider provider;

    private QueryManagerImplement(QueryProvider provider) {
        this.provider = provider;
    }
    public static QueryManager of(QueryProvider provider) {
        return new QueryManagerImplement(provider);
    }

    @Override
    public Query find(Long id, DataSource dataSource) {
        // cache
        if(isCached(id)){
            return cacheMap.get(id);
        }

        // repository
        Optional<Query> mayQuery = provider.find(id, dataSource);
        if(mayQuery.isPresent()){
            cacheMap.put(id, mayQuery.get());
            return mayQuery.get();
        }

        // fail
        return null;
    }
    @Override
    public Query create(QueryCreateCommand command, DataSource dataSource){
        Query query = provider.create(command, dataSource);
        cacheMap.put(query.getId(), query);
        return query;
    }

    @Override
    public Query update(QueryUpdateCommand command, DataSource dataSource){
        Query query = provider.update(command, dataSource);
        cacheMap.put(query.getId(), query);
        return query;
    }

    @Override
    public void clearCache() {
        cacheMap.clear();
    }

    @Override
    public boolean isCached(Long id) {
        return cacheMap.containsKey(id);
    }

}
