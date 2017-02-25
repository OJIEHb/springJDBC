package com.geiko.services;

import com.geiko.dao.WheelDao;
import com.geiko.model.Wheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WheelService implements WheelDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertWheel;
    private TyresService tyresService;

    @Autowired
    public WheelService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.tyresService = new TyresService(dataSource);
        this.insertWheel = new SimpleJdbcInsert(dataSource)
                .withTableName("wheel").usingGeneratedKeyColumns("id");
    }

    public List<Wheel> getWheelsByCarId(int id) {
        List<Wheel> wheels = jdbcTemplate.query(
                "SELECT * FROM wheel where car_id = ?",
                new Object[] {id},
                new RowMapper<Wheel>() {
                    public Wheel mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Wheel wheel = new Wheel();
                        wheel.setId(rs.getInt("id"));
                        wheel.setCarId(rs.getInt("car_id"));
                        wheel.setTyres(tyresService.getTyresByWheelId(wheel.getId()));
                        return wheel;
                    }
                });
        return wheels;
    }

    public void addWheel(Wheel wheel) {
        Map<String, Object> parameters = new HashMap<String, Object>(1);
        parameters.put("car_id", wheel.getCarId());
        Number newId = insertWheel.executeAndReturnKey(parameters);
        wheel.setId(newId.intValue());
        wheel.getTyres().setWheelId(newId.intValue());
        tyresService.addTyres(wheel.getTyres());
    }
}
