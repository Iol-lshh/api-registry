package org.lshh.skeleton.core.resource.query.implement;

import lombok.RequiredArgsConstructor;
import org.lshh.skeleton.core.resource.query.QueryProvider;
import org.lshh.skeleton.core.resource.query.QueryRepository;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class QueryProviderImplement implements QueryProvider {
    private final QueryRepository repository;


}
