package com.example.alexrosenfeld10.pulling_it_all_together;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnDoubleTapListener, GestureDetector.OnGestureListener {

    private final int MIN_SWIPE_DISTANCE = 100;

    private GestureDetectorCompat GD;
    private ImageView spicy;
    private Intent secondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        secondActivity = new Intent(getApplicationContext(), SecondActivity.class);
        spicy = (ImageView) findViewById(R.id.spicy);

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
        Animation myRotation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        spicy.startAnimation(myRotation);
        return true;
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
    public boolean onFling(MotionEvent e1, MotionEvent e2, float v, float v1) {
        // left to right swipe
        if (e2.getX() - e1.getX() >= MIN_SWIPE_DISTANCE) {
            startActivity(secondActivity);
            return true;
        }
        else {
            return false;
        }
    }
}
