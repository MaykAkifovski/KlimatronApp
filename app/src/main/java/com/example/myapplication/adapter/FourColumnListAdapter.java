package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Sensor;

import java.util.List;

import static java.lang.String.valueOf;

public class FourColumnListAdapter extends ArrayAdapter<Sensor> {

    private LayoutInflater mInflater;
    private List<Sensor> sensors;
    private int mViewResourceId;

    public FourColumnListAdapter(Context context, int textViewResourceId, List<Sensor> sensors) {
        super(context, textViewResourceId, sensors);
        this.sensors = sensors;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        Sensor sensor = sensors.get(position);

        if (sensor != null) {
            TextView sensorName = convertView.findViewById(R.id.textSensorName);
            TextView t = convertView.findViewById(R.id.textT);
            TextView tMin = convertView.findViewById(R.id.textTmin);
            TextView tMax = convertView.findViewById(R.id.textTmax);
            if (sensorName != null) {
                sensorName.setText(sensor.getName());
            }
            if (t != null) {
                t.setText(valueOf(sensor.getTemperature()));
            }
            if (tMin != null) {
                tMin.setText(valueOf(sensor.getMin()));
            }
            if (tMax != null) {
                tMax.setText(valueOf(sensor.getMax()));
            }
        }

        return convertView;
    }
}
