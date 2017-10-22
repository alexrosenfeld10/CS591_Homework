package com.example.sse.interfragmentcommunication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

//public class MainActivity extends AppCompatActivity {
    public class MainActivity extends AppCompatActivity implements ControlFragment.ControlFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//Honoring our promise to implement sendMessage.
    @Override
    public void sendMessage(String msg, String msg2) {
        BottomFragment receivingFragment = (BottomFragment)getSupportFragmentManager().findFragmentById(R.id.bottomFragment);
        receivingFragment.setFunnyMessage(msg, msg2);
    }



}


//Toast.makeText(getBaseContext(),"I would like to propose a Toast.", Toast.LENGTH_LONG).show();
