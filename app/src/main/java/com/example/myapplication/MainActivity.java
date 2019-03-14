package com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.database.DatabaseSingleton;
import com.example.myapplication.database.MessageParser;
import com.example.myapplication.helper.MqttHelper;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {

    MqttHelper mqttHelper;
    MessageParser messageParser;
    DatabaseSingleton dbSingleton;

    Toolbar toolbarApp;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        messageParser = new MessageParser(this);
        dbSingleton = DatabaseSingleton.getInstance();

        toolbarApp = findViewById(R.id.toolbarApp);
        toolbarApp.setTitle(getResources().getString(R.string.app_name));

        startMqtt();

//        listView.setOnItemClickListener((parent, view, position, id) -> {
//            Intent intent = new Intent(this, BoardActivity.class);
//            IdAndBoardName itemAtPosition = (IdAndBoardName) listView.getItemAtPosition(position);
//            intent.putExtra("id", itemAtPosition.getId());
//            intent.putExtra("boardName", itemAtPosition.getBoardName());
//            startActivity(intent);
//        });
    }

    private void startMqtt() {
        mqttHelper = new MqttHelper(getApplicationContext());
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {

            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) {
                if (topic.contains("SENSOR")) {
                    Log.w("Debug", mqttMessage.toString());

                    MessageParser data = MainActivity.this.messageParser.parseMqttMessage(topic.replace("/SENSOR", ""), mqttMessage.toString());
                    dbSingleton.addData(data);
                    updateData();
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

    private void updateData() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dbSingleton.getBoardNames());
        listView = findViewById(R.id.listViewBoard);
        listView.setAdapter(adapter);
    }
}
