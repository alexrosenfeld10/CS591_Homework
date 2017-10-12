package com.example.tkixi.c7_p28;

import android.animation.ObjectAnimator;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.GestureDetector;
import android.support.v4.view.GestureDetectorCompat;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    private GestureDetectorCompat GD;
    TextView textFling;
    FrameLayout mainScreen;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;
    private static final int MIN_SWIPE_DISTANCE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textFling = (TextView) findViewById(R.id.textThing);
        mainScreen = (FrameLayout) findViewById(R.id.mainScreen);
        GD = new GestureDetectorCompat(this, this);


    }


    // Set up our gesture detector to receive touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);
        textFling.setClickable(true);
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
    public void onShowPress(MotionEvent e) {}

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {}

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        int offsetY = displayMetrics.heightPixels - mainScreen.getMeasuredHeight();

        int[] location = new int[2];
        textFling.getLocationOnScreen(location);
        float orgX = location[0];
        float orgY = location[1] - offsetY;

        float stopX = orgX + e2.getX()-e1.getX();
        float stopY = orgY + e2.getY()-e1.getY();

        Rect scrollBounds = new Rect();
        textFling.getHitRect(scrollBounds);
        if (textFling.getLocalVisibleRect(scrollBounds)) {
            // If any portion of the textview, even a single pixel, is within the visible window

            if(e1.getX()-e2.getX() > MIN_SWIPE_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(textFling, "translationX", orgX, stopX);
                flingAnimator.setDuration(2000);
                flingAnimator.start();
                // put Textview on left;
            }
            // left to right swipe
            else if (e2.getX() - e1.getX() > MIN_SWIPE_DISTANCE &&
                    Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY){
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(textFling, "translationX", orgX, stopX);
                flingAnimator.setDuration(2000);
                flingAnimator.start();

                // put Textview on right;
            }

            // top to bottom swipe
            else if (e1.getY() - e2.getY() > MIN_SWIPE_DISTANCE &&
                    Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(textFling, "translationY", orgY, stopY);
                flingAnimator.setDuration(2000);
                flingAnimator.start();
                // put Textview on bottom;
            }

            // bottom to top
            else if (e2.getY() - e1.getY() > MIN_SWIPE_DISTANCE &&
                    Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY){
                ObjectAnimator flingAnimator = ObjectAnimator.ofFloat(textFling, "translationY", orgY, stopY);
                flingAnimator.setDuration(2000);
                flingAnimator.start();
                // put Textview on top;
            }
        } else {  // Textview is not in the screen(flinged out!!!)
            int randomX = new Random().nextInt(width);
            int randomY = new Random().nextInt(height);

            textFling.setX(randomX);
            textFling.setY(randomY);

        }

        return true;
    }


}

