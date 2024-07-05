package org.lshh.skeleton.library.resource.query.implement;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public interface JdbcTemplateWrapper {
    static JdbcTemplateWrapper of(DataSource dataSource) {
        return new JdbcTemplateWrapperImplement(dataSource);
    }

    List<Map<String, Object>> queryForList(String queryBody, Map<String, Object> args);

    boolean isReady();
}
