package com.example.myapplication.database;

import com.example.myapplication.model.Board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    public void addData(MessageParser data) {
        Board board = new Board(data);
        arduinoBoardMap.put(board.getBoardName(), board);
    }

    public List<String> getBoardNames() {
        return arduinoBoardMap.keySet().stream().sorted().collect(Collectors.toList());
    }
}
