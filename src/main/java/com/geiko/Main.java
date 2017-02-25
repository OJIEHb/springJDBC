package com.geiko;

import com.geiko.dao.*;
import com.geiko.model.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        CarDao carDao = (CarDao)context.getBean("carService");
        Tyres tyres = new Tyres(15,"Michelin");
        List<Wheel> wheels= new ArrayList<Wheel>();
        for(int i = 0; i < 4; i++){
            wheels.add(new Wheel(tyres));
        }
        Engine engine = new Engine(50);
        Car car = new Car("Mazda",wheels,engine);
        carDao.addCar(car);
        System.out.println(car);
    }
}
