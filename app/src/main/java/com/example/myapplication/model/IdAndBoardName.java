package com.example.myapplication.model;

public class IdAndBoardName {
    private String id;
    private String boardName;

    public IdAndBoardName(String id, String boardName) {
        this.id = id;
        this.boardName = boardName;
    }

    public String getId() {
        return id;
    }

    public String getBoardName() {
        return boardName;
    }
}
