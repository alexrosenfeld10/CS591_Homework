package com.example.megan.c9_p20;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ControlFragment.ControlFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void changeLight() {
        LightFragment receivingFragment = (LightFragment) getSupportFragmentManager().findFragmentById(R.id.lightFragment);
        receivingFragment.setLight();
    }
}
