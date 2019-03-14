package com.example.myapplication.model;

import android.annotation.SuppressLint;

public class Sensor {
    private String id;
    private String name;
    private Double temperature;
    private Double min;
    private Double max;

    public Sensor() {
    }

    public Sensor(String id, String name, Double temperature) {
        this.id = id;
        this.name = name;
        this.temperature = temperature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public String toString() {
        return String.format("Id=%s T=%f C", name == null ? id : name, temperature);
    }
}
