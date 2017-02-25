package com.geiko.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Андрей on 16.02.2017.
 */
@Component
public class Car {
    private int id;
    private String name;
    private List<Wheel> wheels;
    private Engine engine;

    @Autowired
    public Car(List<Wheel> wheels, Engine engine) {
        this.wheels = wheels;
        this.engine = engine;
    }

    public Car(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Wheel> getWheels() {
        return wheels;
    }

    public void setWheels(List<Wheel> wheels) {
        this.wheels = wheels;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Car(String name, List<Wheel> wheels, Engine engine) {
        this.name = name;
        this.wheels = wheels;
        this.engine = engine;
    }

    @Override
    public String toString() {
        return "Car{\n" +
                " id=" + id +
                ",\n name=" + name +
                ",\n wheels=" + wheels +
                ",\n engine=" + engine +
                "\n}";
    }
}
