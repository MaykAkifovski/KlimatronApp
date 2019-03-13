package com.example.myapplication.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataBank {
    private static DataBank instance = null;

    private DataBank() {
    }

    public static DataBank getInstance() {
        if (instance == null)
            instance = new DataBank();
        return instance;
    }

    private Map<String, ArduinoBoard> arduinoBoardMap = new HashMap<>();
    private Map<String, String> clientToBoardName = new HashMap<>();
    private List<String> clients = new ArrayList<>();

    public List<String> getClients() {
        return clients;
    }

    public ArduinoBoard getArduinoBoard(String boardName) {
        return arduinoBoardMap.get(boardName);
    }

    public void addArduinoBoard(ArduinoBoard arduinoBoard) {
        if (clientToBoardName.containsKey(arduinoBoard.getClient())) {
            String boardName = clientToBoardName.get(arduinoBoard.getClient());
            arduinoBoard.setBoardName(boardName);
        } else {
            clientToBoardName.put(arduinoBoard.getClient(), arduinoBoard.getClient());
            arduinoBoard.setBoardName(arduinoBoard.getClient());
        }
        arduinoBoardMap.put(arduinoBoard.getBoardName(), arduinoBoard);
        clients.clear();
        clients.addAll(arduinoBoardMap.keySet().stream().sorted().collect(Collectors.toList()));
    }

    public List<String> getSensorsInfo(String client) {
        return getArduinoBoard(client).getSensors().stream().map(Sensor::toString).collect(Collectors.toList());
    }

    public void changeBoardName(String currentBoardName, String newBoardName) {
        ArduinoBoard arduinoBoard = arduinoBoardMap.get(currentBoardName);
        if (arduinoBoard != null) {
            arduinoBoard.setBoardName(newBoardName);
            clientToBoardName.put(arduinoBoard.getClient(), arduinoBoard.getBoardName());
            arduinoBoardMap.remove(currentBoardName);
            arduinoBoardMap.put(arduinoBoard.getBoardName(), arduinoBoard);
        }
    }
}
