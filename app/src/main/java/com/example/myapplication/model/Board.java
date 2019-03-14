package com.example.myapplication.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private String client;
    private String boardName;
    private LocalDateTime time;
    private List<Sensor> sensors;

    public Board() {
        sensors = new ArrayList<>();
    }

    public String getBoardName() {
        return boardName;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public void setBoardName(String boardName) {
        this.boardName = boardName;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = LocalDateTime.parse(time);
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }

    public void addSensor(Sensor sensor) {
        this.sensors.add(sensor);
    }
}
