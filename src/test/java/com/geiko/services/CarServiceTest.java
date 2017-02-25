package com.geiko.services;

import com.geiko.dao.CarDao;
import com.geiko.model.Car;
import com.geiko.model.Engine;
import com.geiko.model.Tyres;
import com.geiko.model.Wheel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

/**
 * Created by Андрей on 25.02.2017.
 */
public class CarServiceTest {

    @Test
    public void addCar() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        EngineService engineService = mock(EngineService.class);
        CarDao carService = (CarDao)context.getBean("carService");
        Tyres tyres = new Tyres(16,"Rossava");
        List<Wheel> wheels= new ArrayList<Wheel>();
        for(int i = 0; i < 4; i++){
            wheels.add(new Wheel(tyres));
        }
        Engine engine = new Engine(65);
        Car car = new Car("Lanos",wheels,engine);
        doThrow(new SQLException()).when(engineService).addEngine(engine);
        carService.setEngineService(engineService);
        carService.addCar(car);
    }
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CarServiceTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}