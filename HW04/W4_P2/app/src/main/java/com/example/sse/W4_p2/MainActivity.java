package com.example.sse.W4_P2;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

//Imports for hardware sensors
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String TAG = "W4_P2";

    private float lastX, lastY, lastZ;
    private float acceleration;
    private float currentAcceleration;
    private float lastAcceleration;

    private SeekBar seekBar;
    private WebView webSearchView;
    private Button testButton;

    private static int SIGNIFICANT_SHAKE = 50000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // initialize acceleration values
        acceleration = 0.00f;
        currentAcceleration = SensorManager.GRAVITY_EARTH;
        lastAcceleration = SensorManager.GRAVITY_EARTH;
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setProgress(50000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                SIGNIFICANT_SHAKE = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        webSearchView = (WebView)findViewById(R.id.webSearchView);
        WebSettings webSettings = webSearchView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webSearchView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart Triggered.");
        enableAccelerometerListening();
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop Triggered.");
        disableAccelerometerListening();
        super.onStop();
    }

    private void enableAccelerometerListening() {
        // The Activity has a SensorManager Reference.
        // This is how we get the reference to the device's SensorManager.
        SensorManager sensorManager =
                (SensorManager) this.getSystemService(
                        Context.SENSOR_SERVICE);    //The last parm specifies the type of Sensor we want to monitor


        //Now that we have a Sensor Handle, let's start "listening" for movement (accelerometer).
        //3 parms, The Listener, Sensor Type (accelerometer), and Sampling Frequency.
        sensorManager.registerListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);   //don't set this too high, otw you will kill user's battery.
    }



    // disable listening for accelerometer events
    private void disableAccelerometerListening() {

        //Disabling Sensor Event Listener is two step process.
        //1. Retrieve SensorManager Reference from the activity.
        //2. call unregisterListener to stop listening for sensor events
        //THis will prevent interruptions of other Apps and save battery.

        // get the SensorManager
        SensorManager sensorManager =
                (SensorManager) this.getSystemService(
                        Context.SENSOR_SERVICE);

        // stop listening for accelerometer events
        sensorManager.unregisterListener(sensorEventListener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
    }

    private final SensorEventListener sensorEventListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float x = event.values[0];   //always do this first
            float y = event.values[1];
            float z = event.values[2];

            // save previous acceleration value
            lastAcceleration = currentAcceleration;

            // calculate the current acceleration
            currentAcceleration = x * x + y * y + z * z;

            // calculate the change in acceleration
            acceleration = currentAcceleration *  (currentAcceleration - lastAcceleration);

            // if the user shakes really, really hard
            if (acceleration > 10000) {
                Log.i(TAG, "Significant movement, causing dizziness");
                Toast.makeText(MainActivity.this, "Significant movement", Toast.LENGTH_LONG).show();
                webSearchView.loadUrl("https://jumpingjaxfitness.files.wordpress.com/2010/07/dizziness.jpg");
            }

            // if the acceleration is above a certain threshold
            else if (acceleration > SIGNIFICANT_SHAKE) {
                Log.i(TAG, "Significant movement");
                Toast.makeText(MainActivity.this, "Significant movement", Toast.LENGTH_LONG).show();

                float[] delta = new float[3];
                delta[0] = x-lastX;
                delta[1]= y-lastY;
                delta[2] = z-lastZ;

                int maxIndex = 0;
                float maxDelta = delta[0];
                for (int i = 0; i < delta.length; i++) {
                    if (delta[i] > maxDelta) {
                        maxIndex = i;
                        maxDelta = delta[i];
                    }
                }

                switch(maxIndex){
                    case 0:
                        Log.i(TAG, "Google activated");
                        webSearchView.loadUrl("http://www.google.com");
                        break;
                    case 1:
                        Log.i(TAG, "Yahoo activated");
                        webSearchView.loadUrl("http://www.yahoo.com");
                        break;
                    case 2:
                        Log.i(TAG, "Bing activated");
                        webSearchView.loadUrl("http://www.bing.com");
                        break;
                    default:
                        Log.i(TAG, "Default case hit");
                        break;
                }
            }

            lastX = x;
            lastY = y;
            lastZ = z;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
}