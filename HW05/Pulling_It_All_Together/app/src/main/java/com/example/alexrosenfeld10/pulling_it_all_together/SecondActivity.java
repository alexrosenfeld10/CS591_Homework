package com.example.alexrosenfeld10.pulling_it_all_together;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.SeekBar;

public class SecondActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    private float lastX, lastY, lastZ;  //old coordinate positions from accelerometer, needed to calculate delta.
    private float acceleration;
    private float currentAcceleration;
    private float lastAcceleration;

    private SeekBar seekBar;

    // values used to determine whether user shook the device "significantly"
    private static int SIGNIFICANT_SHAKE = 1000;   //tweak this as necessary
    private static int SEMI_SIGNIFICANT_SHAKE = 50;
    private static int ANIMATION_SPEED = 0;

    private final int MIN_SWIPE_DISTANCE = 100;
    private final int MIN_SWIPE_VELOCITY = 60;

    private GestureDetectorCompat GD;
    private ImageView monkaS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        // initialize acceleration values
        acceleration = 0.00f;                                         //Initializing Acceleration data.
        currentAcceleration = SensorManager.GRAVITY_EARTH;            //We live on Earth.
        lastAcceleration = SensorManager.GRAVITY_EARTH;               //Ctrl-Click to see where else we could use our phone.

        monkaS = (ImageView) findViewById(R.id.monkaS);

        GD = new GestureDetectorCompat(this, this);
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ANIMATION_SPEED = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        enableAccelerometerListening();
    }

    @Override
    protected void onStop() {
        disableAccelerometerListening();
        super.onStop();
    }

    // enable listening for accelerometer events
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
            // get x, y, and z values for the SensorEvent
            //each time the event fires, we have access to three dimensions.
            //compares these values to previous values to determine how "fast"
            // the device was shaken.
            //Ref: http://developer.android.com/reference/android/hardware/SensorEvent.html

            float x = event.values[0];   //always do this first
            float y = event.values[1];
            float z = event.values[2];

            // save previous acceleration value
            lastAcceleration = currentAcceleration;

            // calculate the current acceleration
            currentAcceleration = x * x + y * y + z * z;   //This is a simplified calculation, to be real we would need time and a square root.

            // calculate the change in acceleration        //Also simplified, but good enough to determine random shaking.
            acceleration = currentAcceleration * (currentAcceleration - lastAcceleration);

            // if the acceleration is above a certain threshold
            if (acceleration > SIGNIFICANT_SHAKE) {
                Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
                shake.setDuration(shake.getDuration() - ANIMATION_SPEED);
                monkaS.startAnimation(shake);
            } else if (acceleration > SEMI_SIGNIFICANT_SHAKE) {
                if (Math.abs(x - lastX) > Math.abs(y - lastY)) {
                    if (x - lastX > 0) {
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_right);
                        animation.setDuration(animation.getDuration() - ANIMATION_SPEED);
                        monkaS.startAnimation(animation);
                    } else if (x - lastX < 0) {
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_left);
                        animation.setDuration(animation.getDuration() - ANIMATION_SPEED);
                        monkaS.startAnimation(animation);
                    }

                } else {
                    if (y - lastY > 0) {
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_up);
                        animation.setDuration(animation.getDuration() - ANIMATION_SPEED);
                        monkaS.startAnimation(animation);
                    } else if (y - lastY < 0) {
                        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.move_down);
                        animation.setDuration(animation.getDuration() - ANIMATION_SPEED);
                        monkaS.startAnimation(animation);
                    }
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

    // Set up our gesture detector to receive touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // right to left swipe
        if (e1.getX() - e2.getX() >= MIN_SWIPE_DISTANCE) {
            if (velocityX >= MIN_SWIPE_VELOCITY) {
                Animation myRotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cc10);
                monkaS.startAnimation(myRotation);
            } else {
                Animation myRotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.cc5);
                monkaS.startAnimation(myRotation);
            }
            return true;
        }
        // left to right swipe
        else if (e2.getX() - e1.getX() >= MIN_SWIPE_DISTANCE) {
            if (velocityX >= MIN_SWIPE_VELOCITY) {
                Animation myRotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.c10);
                monkaS.startAnimation(myRotation);
            } else {
                Animation myRotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.c5);
                monkaS.startAnimation(myRotation);
            }
            return true;
        }

        // swipe was too small, return false (unconsumed event)
        else {
            return false;
        }
    }
}
