package org.lshh.skeleton.core.resource.query;

import org.lshh.skeleton.core.resource.query.dto.command.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.command.QueryUpdateCommand;

import java.util.Optional;

public interface QueryProvider {
    // 두가지 일
    // 1. QueryContext -> QueryTask 변환 및 제공
    // 2. QueryContext 영속성 관리 명령

    Optional<Query> find(Long id);
    Query create(QueryCreateCommand command);
    Query update(QueryUpdateCommand command);
}
