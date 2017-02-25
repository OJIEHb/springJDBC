package com.geiko.dao;

import com.geiko.model.Wheel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface WheelDao {
    public List<Wheel> getWheelsByCarId(int id);
    public void addWheel(Wheel wheel);
}
