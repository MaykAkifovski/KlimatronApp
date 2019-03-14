package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class BoardActivity extends AppCompatActivity {

//    DataBank instance = DataBank.getInstance();

    Toolbar toolbarBoard;
    ListView listViewSensor;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        toolbarBoard = findViewById(R.id.toolbarBoard);

        String client = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            client = bundle.getString("client");
            toolbarBoard.setTitle(client);
        }

        listViewSensor = findViewById(R.id.listViewSensor);
//        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, instance.getSensorsInfo(client));
        listViewSensor.setAdapter(arrayAdapter);


        String currentBoardName = client;
        toolbarBoard.setOnClickListener(toolbarBoardView -> {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(BoardActivity.this);
            View mView = getLayoutInflater().inflate(R.layout.dialog_change_board_name, null);
            EditText mBoardName = mView.findViewById(R.id.etBoardName);
            Button mSave = mView.findViewById(R.id.saveBoardButton);
            mBuilder.setView(mView);
            AlertDialog dialog = mBuilder.create();
            dialog.show();
            mSave.setOnClickListener(buttonView -> {
                if (!mBoardName.getText().toString().isEmpty()) {
                    String newBoardName = mBoardName.getText().toString();
//                    instance.changeBoardName(currentBoardName, newBoardName);
                    arrayAdapter.notifyDataSetChanged();
                    toolbarBoard.setTitle(newBoardName);
                    Toast.makeText(
                            BoardActivity.this,
                            String.format("Board %s change to %s", currentBoardName, newBoardName),
                            Toast.LENGTH_SHORT
                    ).show();

                }
                dialog.dismiss();
            });
        });
    }
}
