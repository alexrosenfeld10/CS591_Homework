package com.example.alexrosenfeld10.w6_p1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends AppCompatActivity implements com.example.alexrosenfeld10.w6_p1.ControlFragment.ControlFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Honoring our promise to implement sendMessage.
    @Override
    public void sendMessage(String msg) {
        com.example.alexrosenfeld10.w6_p1.BottomFragment receivingFragment = (com.example.alexrosenfeld10.w6_p1.BottomFragment)getSupportFragmentManager().findFragmentById(R.id.bottomFragment);
        receivingFragment.setFunnyMessage(msg);
        receivingFragment.setImage(msg);
    }



}


//Toast.makeText(getBaseContext(),"I would like to propose a Toast.", Toast.LENGTH_LONG).show();
