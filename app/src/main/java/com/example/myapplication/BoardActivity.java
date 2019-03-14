package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.example.myapplication.adapter.FourColumnListAdapter;
import com.example.myapplication.database.DatabaseSingleton;

public class BoardActivity extends AppCompatActivity {

    DatabaseSingleton dbSingleton;

    Toolbar toolbarBoard;
    ListView listViewSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        dbSingleton = DatabaseSingleton.getInstance();

        toolbarBoard = findViewById(R.id.toolbarBoard);

        String boardName = null;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            boardName = bundle.getString("boardName");
            toolbarBoard.setTitle(boardName);
        }

        listViewSensor = findViewById(R.id.listViewSensor);
        updateData(boardName);
    }

    private void updateData(String boardName) {
        FourColumnListAdapter adapter = new FourColumnListAdapter(this, R.layout.list_adapter_view, dbSingleton.getSensorsByBoardName(boardName));
        listViewSensor.setAdapter(adapter);
    }


//        String currentBoardName = client;
//        toolbarBoard.setOnClickListener(toolbarBoardView -> {
//            AlertDialog.Builder mBuilder = new AlertDialog.Builder(BoardActivity.this);
//            View mView = getLayoutInflater().inflate(R.layout.dialog_change_board_name, null);
//            EditText mBoardName = mView.findViewById(R.id.etBoardName);
//            Button mSave = mView.findViewById(R.id.saveBoardButton);
//            mBuilder.setView(mView);
//            AlertDialog dialog = mBuilder.create();
//            dialog.show();
//            mSave.setOnClickListener(buttonView -> {
//                if (!mBoardName.getText().toString().isEmpty()) {
//                    String newBoardName = mBoardName.getText().toString();
//        instance.changeBoardName(currentBoardName, newBoardName);
//                    arrayAdapter.notifyDataSetChanged();
//                    toolbarBoard.setTitle(newBoardName);
//                    Toast.makeText(
//                            BoardActivity.this,
//                            String.format("Board %s change to %s", currentBoardName, newBoardName),
//                            Toast.LENGTH_SHORT
//                    ).show();
//
//                }
//                dialog.dismiss();
//            });
//        });
}
