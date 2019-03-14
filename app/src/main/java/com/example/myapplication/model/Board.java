package com.example.myapplication.model;

import com.example.myapplication.database.MessageParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Board {

    private String client;
    private String boardName;
    private LocalDateTime time;
    private List<Sensor> sensors;

    public Board(MessageParser data) {
        client = data.client;
        boardName = data.boardName;
        time = LocalDateTime.parse(data.time);
        sensors = new ArrayList<>(data.sensors);
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

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
