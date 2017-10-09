package com.example.jake.c7_p30;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Space spaceTop;
    private TextView operand1,
            addOperator,
            operand2,
            answerView;
    private int o1, o2;
    private GestureDetector detector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spaceTop = (Space) findViewById(R.id.spaceTop);
        operand1 = (TextView) findViewById(R.id.operand1);
        addOperator = (TextView) findViewById(R.id.addOperator);
        operand2 = (TextView) findViewById(R.id.operand2);
        answerView = (TextView) findViewById(R.id.answerView);

        DoubleTapHandler doubleTapHandler = new DoubleTapHandler();
        detector = new GestureDetector(this, doubleTapHandler);
        detector.setOnDoubleTapListener(doubleTapHandler);

        generateRandomOperands();
    }

    private void generateRandomOperands() {
        Random rand = new Random();
        o1 = rand.nextInt(9) + 1;
        o2 = rand.nextInt(9) + 1;
        operand1.setText(String.valueOf(o1));
        operand2.setText(String.valueOf(o2));

        answerView.setText("");
    }

    private boolean inAddOperatorView(int x, int y) {
        Log.i("C7_P30", "inAddOperatorView called");

        int minY = spaceTop.getHeight();
        int maxY = minY + addOperator.getHeight();
        int minX = operand1.getWidth();
        int maxX = minX + addOperator.getWidth();

        if (minX < x && x < maxX && minY < y && y < maxY) {
            return true;
        } else {
            return false;
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        detector.onTouchEvent(event);
        return true;
    }

    private class DoubleTapHandler extends GestureDetector.SimpleOnGestureListener {
        public boolean onDoubleTapEvent(MotionEvent e) {
            int touchX = (int) e.getRawX();
            int touchY = (int) e.getRawY();

            if (inAddOperatorView(touchX, touchY)) {
                int sum = o1 + o2;
                answerView.setText(String.valueOf(sum));
            } else {
                generateRandomOperands();
            }

            return true;
        }
    }
}
