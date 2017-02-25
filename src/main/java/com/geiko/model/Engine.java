package com.geiko.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Андрей on 16.02.2017.
 */
@Component
public class Engine {
    private int id;
    private int engineCapacity;
    private int carId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Engine(int engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Autowired
    public Engine() {
    }

    @Override
    public String toString() {
        return "Engine{" +
                "id=" + id +
                ", engineCapacity=" + engineCapacity +
                ", carId=" + carId +
                '}';
    }
}
