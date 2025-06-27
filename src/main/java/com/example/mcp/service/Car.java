package com.example.mcp.service;

public class Car {
    public String name;
    public String make;
    public String color;

    public Car(String name, String make, String color) {
        this.name = name;
        this.make = make;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
