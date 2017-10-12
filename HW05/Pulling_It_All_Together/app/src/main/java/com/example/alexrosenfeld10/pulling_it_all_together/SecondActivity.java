package com.example.alexrosenfeld10.pulling_it_all_together;

import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

//TODO add accelerometer detection and animations in response
public class SecondActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    private final int MIN_SWIPE_DISTANCE = 100;
    private final int MIN_SWIPE_VELOCITY = 60;

    private GestureDetectorCompat GD;
    private ImageView monkaS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        monkaS = (ImageView) findViewById(R.id.monkaS);

        GD = new GestureDetectorCompat(this, this);
    }

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
