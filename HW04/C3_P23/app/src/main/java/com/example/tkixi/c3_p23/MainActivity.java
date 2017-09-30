package com.example.tkixi.c3_p23;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView red, yellow, green;
    String states = "red";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        red = (TextView) findViewById(R.id.red);
        green = (TextView) findViewById(R.id.green);
        yellow = (TextView) findViewById(R.id.yellow);
        red.setBackgroundColor(Color.RED);
        Button change = (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {

                                      @Override
                                      public void onClick(View view) {{
                                              if(states.equals("red"))
                                              {
                                                  states ="yellow";
                                                  red.setBackgroundColor(Color.RED);
                                                  green.setBackgroundColor(Color.TRANSPARENT);
                                              }
                                              else if(states.equals("yellow")) {
                                                  states = "green";
                                                  yellow.setBackgroundColor(Color.YELLOW);
                                                  red.setBackgroundColor(Color.TRANSPARENT);
                                              }
                                              else { // state = green
                                                  states = "red";
                                                  green.setBackgroundColor(Color.GREEN);
                                                  yellow.setBackgroundColor(Color.TRANSPARENT);

                                              }
                                          }}







                                  }
        );






    }



}
