package edu.tecii.android.appsensores;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager smanager;
    Sensor giroscopio;
    TextView tv1, tv2, tv3;
    EditText etx1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        etx1 = (EditText) findViewById(R.id.editText);

        smanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        giroscopio = smanager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        etx1.setText("");
        List<Sensor> list = smanager.getSensorList(Sensor.TYPE_ALL);
        for (Sensor s: list) {
            etx1.append(s.getName()+"\n");
        }
    }

    public void onPause() {
        super.onPause();
        smanager.unregisterListener(this);
    }

    public void onResume() {
        super.onResume();
        smanager.registerListener(this, giroscopio, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        tv1.setText("Valor de x = " + sensorEvent.values[0]);
        tv2.setText("Valor de y = " + sensorEvent.values[1]);
        tv3.setText("Valor de z = " + sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
