package org.lshh.skeleton.library.resource.query.implement;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

public class JdbcTemplateWrapperImplement implements JdbcTemplateWrapper{

    private final NamedParameterJdbcOperations jdbcTemplate;
    public JdbcTemplateWrapperImplement(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<Map<String, Object>> queryForList(String sql, Map<String, Object> args) {
        return jdbcTemplate.queryForList(sql, args);
    }

    @Override
    public boolean isReady() {
        try{
            this.jdbcTemplate.getJdbcOperations().execute("SELECT 1");
            return true;
        }catch (Exception exception){
            return false;
        }
    }
}
