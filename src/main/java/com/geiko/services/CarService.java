package com.geiko.services;

        import com.geiko.dao.CarDao;
        import com.geiko.model.Car;
        import com.geiko.model.Wheel;
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
public class CarService implements CarDao{
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertCar;
    private WheelService wheelService;
    private EngineService engineService;

    @Autowired
    public CarService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.engineService = new EngineService(dataSource);
        this.wheelService = new WheelService(dataSource);
        this.insertCar = new SimpleJdbcInsert(dataSource)
                .withTableName("car").usingGeneratedKeyColumns("id");
    }

    public CarService(WheelService wheelService, EngineService engineService) {
        this.wheelService = wheelService;
        this.engineService = engineService;
    }

    public Car getCar(int id) {
        Car car = this.jdbcTemplate.queryForObject(
                "select * from car where id = ?",
                new Object[]{id},
                new RowMapper<Car>() {
                    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Car tempCar = new Car();
                        tempCar.setId(rs.getInt("id"));
                        tempCar.setName(rs.getString("name"));
                        tempCar.setWheels(wheelService.getWheelsByCarId(tempCar.getId()));
                        tempCar.setEngine(engineService.getEngineByCarId(tempCar.getId()));
                        return tempCar;
                    }
                });
        return car;
    }

    public void addCar(Car car){
        try {
            Map<String, Object> parameters = new HashMap<String, Object>(1);
            parameters.put("name", car.getName());
            Number newId = insertCar.executeAndReturnKey(parameters);
            car.setId(newId.intValue());
            car.getEngine().setCarId(newId.intValue());
            engineService.addEngine(car.getEngine());
            for (Wheel wheel : car.getWheels()) {
                wheel.setCarId(newId.intValue());
                wheelService.addWheel(wheel);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
