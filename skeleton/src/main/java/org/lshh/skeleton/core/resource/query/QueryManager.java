package org.lshh.skeleton.core.resource.query;

import org.lshh.skeleton.core.resource.query.dto.QueryCreateCommand;
import org.lshh.skeleton.core.resource.query.dto.QueryUpdateCommand;

import javax.sql.DataSource;

public interface QueryManager {
    Query create(QueryCreateCommand command, DataSource dataSource);

    Query update(QueryUpdateCommand command, DataSource dataSource);

    void clearCache();

    boolean isCached(Long id);
    // Query 실행?
    // provider 관리
    // Query 영속성 명령 전달

    // Query 실행
    // 리소서 주입 받음
    // 리소서 쿼리
}
