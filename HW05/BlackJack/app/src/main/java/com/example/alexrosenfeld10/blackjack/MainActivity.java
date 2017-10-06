package com.example.alexrosenfeld10.blackjack;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private TextView txtCurrentTotal;
    private GestureDetectorCompat GD;

    private HashMap<Integer, ArrayList<String>> deck = new HashMap<>();
    ArrayList<String> suits = new ArrayList<String>() {{
        add("spades");
        add("hearts");
        add("clubs");
        add("diamonds");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtCurrentTotal = (TextView) findViewById(R.id.txtCurrentTotal);

        GD = new GestureDetectorCompat(this, this);

        resetDeck();
    }

    private void resetDeck() {
        deck.clear();

        for (int i = 2; i <= 14; i++) {
            deck.put(i, suits);
        }
    }

    // Set up our gesture detector to receive touch events
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        // hit the player a new card
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        // re deal
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
        return false;
    }
}
