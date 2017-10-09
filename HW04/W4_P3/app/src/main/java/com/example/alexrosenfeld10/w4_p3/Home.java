package com.example.alexrosenfeld10.w4_p3;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class Home extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private GestureDetectorCompat GD;

    private Intent northIntent;
    private Intent eastIntent;
    private Intent southIntent;
    private Intent westIntent;

    private static final int MIN_SWIPE_DISTANCE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GD = new GestureDetectorCompat(this, this);

        northIntent = new Intent(getApplicationContext(), North.class);
        eastIntent = new Intent(getApplicationContext(), East.class);
        southIntent = new Intent(getApplicationContext(), South.class);
        westIntent = new Intent(getApplicationContext(), West.class);
    }

    // Set up our gesture detector to receive touch events
        @Override
        public boolean onTouchEvent(MotionEvent event) {
            this.GD.onTouchEvent(event);
            return super.onTouchEvent(event);
        }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        // right to left swipe
        if (e1.getX() - e2.getX() >= MIN_SWIPE_DISTANCE) {
            startActivity(westIntent);
        }
        // left to right swipe
        else if (e2.getX() - e1.getX() >= MIN_SWIPE_DISTANCE) {
            startActivity(eastIntent);
        }

        // top to bottom swipe
        else if (e2.getY() - e1.getY() >= MIN_SWIPE_DISTANCE) {
            startActivity(southIntent);
        }

        // bottom to top
        else if (e1.getY() - e2.getY() >= MIN_SWIPE_DISTANCE) {
            startActivity(northIntent);
        }

        // swipe was too small, return false (unconsumed event)
        else {
            return false;
        }

        return true;
    }
}
