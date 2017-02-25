package com.geiko.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Tyres {

    private int id;
    private int size;
    private String name;
    private int wheelId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWheelId() {
        return wheelId;
    }

    public void setWheelId(int wheelId) {
        this.wheelId = wheelId;
    }

    public Tyres(int size, String name) {
        this.size = size;
        this.name = name;
    }

    public Tyres(int size, String name, int wheelId) {
        this.size = size;
        this.name = name;
        this.wheelId = wheelId;
    }

    @Autowired
    public Tyres() {
    }

    @Override
    public String toString() {
        return "Tyres{" +
                "id=" + id +
                ", size=" + size +
                ", name='" + name + '\'' +
                ", wheelId=" + wheelId +
                '}';
    }
}
