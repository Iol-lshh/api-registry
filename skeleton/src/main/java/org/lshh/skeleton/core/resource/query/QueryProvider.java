package org.lshh.skeleton.core.resource.query;

import org.lshh.skeleton.core.resource.query.dto.command.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.command.QueryUpdateCommand;

public interface QueryProvider {
    // 두가지 일
    // 1. QueryContext -> QueryTask 변환 및 제공
    // 2. QueryContext 영속성 관리 명령

    QueryTask find(Long id);
    QueryTask create(QueryCreateCommand command);
    QueryTask update(QueryUpdateCommand command);
}
