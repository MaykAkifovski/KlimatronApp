package com.example.myapplication.database;

import com.example.myapplication.model.Board;
import com.example.myapplication.model.Sensor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class DatabaseSingleton {
    private static DatabaseSingleton instance = null;

    private DatabaseSingleton() {
    }

    public static DatabaseSingleton getInstance() {
        if (instance == null)
            instance = new DatabaseSingleton();
        return instance;
    }

    private Map<String, Board> arduinoBoardMap = new HashMap<>();

    public void addData(Board board) {
        arduinoBoardMap.put(board.getBoardName(), board);
    }

    public List<String> getBoardNames() {
        return arduinoBoardMap.keySet().stream().sorted().collect(Collectors.toList());
    }

    public List<Sensor> getSensorsByBoardName(String boardName) {
        if (boardName != null) {
            Board board = arduinoBoardMap.get(boardName);
            if (board != null) {
                return board.getSensors();
            }
        }
        return emptyList();
    }
}
