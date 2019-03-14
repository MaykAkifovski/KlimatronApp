package com.example.myapplication.database;

import com.example.myapplication.MainActivity;
import com.example.myapplication.model.Board;
import com.example.myapplication.model.Sensor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class MessageParser {
    private static final String INNEN = "Innen";
    private static final String AUSSEN = "Ausssen";
    private static final String VERFLUESSIGEN = "Verfluessigen";
    private static final String VERDANPFEN = "Verdanpfen";

    private ObjectMapper objectMapper;
    private DatabaseHelper dbHelper;

    public MessageParser(MainActivity mainActivity) {
        this.objectMapper = new ObjectMapper();
        this.dbHelper = new DatabaseHelper(mainActivity);
    }

    public Board parseMqttMessage(String client, String message) {
        Board board = new Board();
        board.setClient(client);
        board.setBoardName(dbHelper.getBoardName(client));
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            jsonNode.fields().forEachRemaining(entry -> parseField(board, entry));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return board;
    }

    private void parseField(Board board, Map.Entry<String, JsonNode> field) {
        if (field.getKey().equals("Time")) {
            board.setTime(field.getValue().asText());
        } else if (field.getKey().contains("DS18B20") && field.getValue().has("Id")) {
            Sensor sensor = new Sensor();
            sensor.setId(field.getValue().get("Id").asText());
            setSensorName(sensor, field.getKey());
            sensor.setTemperature(field.getValue().get("Temperature").asDouble());
            board.addSensor(sensor);
        }
    }

    private void setSensorName(Sensor sensor, String key) {
        switch (key) {
            case "DS18B20-1":
                sensor.setName(INNEN);
                break;
            case "DS18B20-2":
                sensor.setName(AUSSEN);
                break;
            case "DS18B20-3":
                sensor.setName(VERFLUESSIGEN);
                break;
            case "DS18B20-4":
                sensor.setName(VERDANPFEN);
                break;
        }
    }
}