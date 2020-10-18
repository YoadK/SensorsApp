package com.example.sensorsapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sm;
    private TextView textView1;
    private List<Sensor> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        getData();
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (list.size() > 0) {
            sm.unregisterListener(sel);
        }
    }

    private void initUI() {
        textView1 = findViewById(R.id.textView1);
    }

    private void getData() {
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);

        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (list.size() > 0) {
            sm.registerListener(sel, list.get(0), SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
        }
    }

    SensorEventListener sel = new SensorEventListener() {
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            textView1.setText("x: " + values[0] + "\ny: " + values[1] + "\nz: " + values[2]);
        }
    };

}
