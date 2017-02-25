package com.geiko.dao;

import com.geiko.model.Car;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CarDao {
    public Car getCar(int id);
    public void addCar(Car car);
}
