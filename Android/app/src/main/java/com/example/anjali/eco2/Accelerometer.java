package com.example.anjali.eco2;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    private Sensor mysensor;
    private SensorManager Sm;
    private TextView x,y,z;
    private float[] gravSensorVals;
    private long lastUpdate = 0;
    private int var_time=10000;// milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);
        Sm=(SensorManager)getSystemService(SENSOR_SERVICE);
        mysensor=Sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        Sm.registerListener(this,mysensor,SensorManager.SENSOR_DELAY_NORMAL);
        x=(TextView)findViewById(R.id.x);
        y=(TextView)findViewById(R.id.y);
        z=(TextView)findViewById(R.id.z);
    }
    static final float ALPHA = 0.25f; // if ALPHA = 1 OR 0, no filter applies.

    protected float[] lowPass( float[] input, float[] output ) {
        if ( output == null ) return input;

        for ( int i=0; i<input.length; i++ ) {
            output[i] = output[i] + ALPHA * (input[i] - output[i]);
        }
        return output;
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            gravSensorVals = lowPass(sensorEvent.values.clone(), gravSensorVals);
            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > var_time) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;
                x.setText("X: " + gravSensorVals[0]);
                y.setText("Y: " + gravSensorVals[1]);
                z.setText("Z: " + gravSensorVals[2]);
            }
        }


        //x.setText("X: "+ lowPass(sensorEvent.values.clone(),gravSensorVals));
       // y.setText("Y: "+ sensorEvent.values[1]);
        //z.setText("Z: "+ sensorEvent.values[2]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    protected void onPause() {
        super.onPause();
        Sm.unregisterListener(this);
    }
    protected void onResume() {
        super.onResume();
        Sm.registerListener(this, mysensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

}
