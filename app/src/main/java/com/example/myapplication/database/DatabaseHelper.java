package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "boards.db";
    private static final String TABLE_NAME = "boards_table";
    private static final String ID = "ID";
    private static final String CLIENT = "CLIENT";
    private static final String BOARD_NAME = "BOARD_NAME";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        context.deleteDatabase(DATABASE_NAME);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CLIENT + " TEXT, " +
                BOARD_NAME + " TEXT " +
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    public String getBoardName(String client) {
        String boardName = null;
        SQLiteDatabase writableDatabase = this.getWritableDatabase();
        Cursor cursor = writableDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            String _client = cursor.getString(1);
            if (_client.equals(client)) {
                boardName = cursor.getString(2);
                break;
            }
        }
        if (boardName == null) {
            boardName = client;
            addNewClient(client);
        }
        cursor.close();
        return boardName;
    }

    private void addNewClient(String client) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CLIENT, client);
        values.put(BOARD_NAME, client);
        db.insert(TABLE_NAME, null, values);
    }
}
