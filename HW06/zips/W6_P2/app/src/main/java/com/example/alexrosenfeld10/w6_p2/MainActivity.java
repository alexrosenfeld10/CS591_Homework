package com.example.alexrosenfeld10.w6_p2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements ButtonsFragment.ButtonsFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Honoring our promise to implement sendMessage.
    @Override
    public void sendMessage(String msg) {
        DrawableFragment receivingFragment = (DrawableFragment) getSupportFragmentManager().findFragmentById(R.id.drawableFragment);
        receivingFragment.process(msg);
    }
}
