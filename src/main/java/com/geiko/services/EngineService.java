package com.geiko.services;

import com.geiko.dao.EngineDao;
import com.geiko.model.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class EngineService implements EngineDao {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertEngine;

    @Autowired
    public EngineService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertEngine = new SimpleJdbcInsert(dataSource)
                .withTableName("engine").usingGeneratedKeyColumns("id");
    }

    public Engine getEngineByCarId(int id) {
        Engine engine = this.jdbcTemplate.queryForObject(
                "select * from engine where car_id = ?",
                new Object[]{id},
                new RowMapper<Engine>() {
                    public Engine mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Engine tempEngine = new Engine();
                        tempEngine.setId(rs.getInt("id"));
                        tempEngine.setEngineCapacity(rs.getInt("engine_capacity"));
                        tempEngine.setCarId(rs.getInt("car_id"));
                        return tempEngine;
                    }
                });
        return engine;
    }

    public void addEngine(Engine engine) {
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("engine_capacity", engine.getEngineCapacity());
        parameters.put("car_id", engine.getCarId());
        Number newId = insertEngine.executeAndReturnKey(parameters);
        engine.setId(newId.intValue());
    }
}
