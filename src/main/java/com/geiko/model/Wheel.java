package com.geiko.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Андрей on 16.02.2017.
 */
@Component
public class Wheel {
    private int id;
    private Tyres tyres;
    private int carId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tyres getTyres() {
        return tyres;
    }

    public void setTyres(Tyres tyres) {
        this.tyres = tyres;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Wheel() {
    }

    @Autowired
    public Wheel(Tyres tyres) {
        this.tyres = tyres;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "id=" + id +
                ", tyres=" + tyres +
                ", carId=" + carId +
                '}';
    }
}
