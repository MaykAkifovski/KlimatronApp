package com.example.myapplication.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArduinoBoard {

    private String client;
    private String boardName;
    private LocalDateTime time;
    private List<Sensor> sensors = new ArrayList<>();
    private String tempUnit;


    public ArduinoBoard(String client, String message) {
        this.client = client;
        try {
            JsonNode jsonNode = new ObjectMapper().readTree(message);
            jsonNode.fields().forEachRemaining(this::parseField);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseField(Map.Entry<String, JsonNode> field) {
        if (field.getKey().equals("Time")) {
            this.time = LocalDateTime.parse(field.getValue().asText());
        } else if (field.getValue().has("Id")) {
            sensors.add(new Sensor(field.getValue().get("Id").asText(), field.getValue().get("Temperature").asDouble()));
        } else {
            this.tempUnit = field.getValue().asText();
        }
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

    public String getTempUnit() {
        return tempUnit;
    }

    public void setTempUnit(String tempUnit) {
        this.tempUnit = tempUnit;
    }

    public String getListViewText() {
        return boardName + sensors.toString();
    }
}
