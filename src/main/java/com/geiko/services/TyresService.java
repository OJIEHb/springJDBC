package com.geiko.services;

import com.geiko.dao.TyresDao;
import com.geiko.model.Tyres;
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

/**
 * Created by Андрей on 17.02.2017.
 */
@Service
public class TyresService implements TyresDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertTyres;

    @Autowired
    public TyresService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertTyres = new SimpleJdbcInsert(dataSource)
                .withTableName("tyres").usingGeneratedKeyColumns("id");
    }

    public Tyres getTyresByWheelId(int id) {
        Tyres tyres = this.jdbcTemplate.queryForObject(
                "select * from tyres where wheel_id = ?",
                new Object[]{id},
                new RowMapper<Tyres>() {
                    public Tyres mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Tyres tempTyres = new Tyres();
                        tempTyres.setId(rs.getInt("id"));
                        tempTyres.setName(rs.getString("name"));
                        tempTyres.setSize(rs.getInt("size"));
                        tempTyres.setWheelId(rs.getInt("wheel_id"));
                        return tempTyres;
                    }
                });
        return tyres;
    }

    public void addTyres(Tyres tyres) {
        Map<String, Object> parameters = new HashMap<String, Object>(3);
        parameters.put("name", tyres.getName());
        parameters.put("size", tyres.getSize());
        parameters.put("wheel_id",tyres.getWheelId());
        Number newId = insertTyres.executeAndReturnKey(parameters);
        tyres.setId(newId.intValue());
    }
}
