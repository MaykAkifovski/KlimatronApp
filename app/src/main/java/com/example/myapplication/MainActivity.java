package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.myapplication.helper.MqttHelper;
import com.example.myapplication.model.ArduinoBoard;
import com.example.myapplication.model.DataBank;
import com.example.myapplication.model.Sensor;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity extends AppCompatActivity {

    MqttHelper mqttHelper;

    Toolbar toolbarApp;
    ListView listViewBoard;
    ArrayAdapter<String> arrayAdapter;

    private DataBank instance = DataBank.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarApp = findViewById(R.id.toolbarApp);
        toolbarApp.setTitle(getResources().getString(R.string.app_name));

        listViewBoard = findViewById(R.id.listViewBoard);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, instance.getClients());
        listViewBoard.setAdapter(arrayAdapter);

        listViewBoard.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, BoardActivity.class);
            intent.putExtra("client", listViewBoard.getItemAtPosition(position).toString());
            startActivity(intent);
        });

        startMqtt();
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

                    ArduinoBoard arduinoBoard = new ArduinoBoard(topic.replace("/SENSOR", ""), mqttMessage.toString());
                    instance.addArduinoBoard(arduinoBoard);
                    arrayAdapter.notifyDataSetChanged();
                    checkTemperature(arduinoBoard);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

    private void checkTemperature(ArduinoBoard arduinoBoard) {
        for (Sensor sensor : arduinoBoard.getSensors()) {
            if (!isTemperatureOkay(sensor)) {
                sendEmail(sensor, arduinoBoard.getBoardName());
            }
        }
    }

    private void sendEmail(Sensor sensor, String client) {

    }

    private boolean isTemperatureOkay(Sensor sensor) {
        if (sensor.getMin() != null && sensor.getMax() != null) {
            return sensor.getTemperature() >= sensor.getMin() || sensor.getTemperature() <= sensor.getMax();
        } else {
            return true;
        }
    }
}
