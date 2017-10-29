package com.example.tkixi.w7_p2;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


//      2b) Bundle code at the bottom

//      2c) Fragment code in SharedFrag.java

//      2d) The Bundle will work from any time that the activity starts during the
//      lifetime of the application. Upon killing the activity(eg. back button,
//      or closing the app), the bundle will fail to restore the app).

//      2e) The SharedPreferences will fail to to restore the App data based on
//      if both implementations of saving data (bundle and sharedpreferences) exist


public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Uncomment out following code to grab as bundle
    // and make sure SharedFrag doesn't inflate its view

//    private CheckBox redBox, greenBox, blueBox;
//    private TextView textView;
//    private EditText editView;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        redBox = (CheckBox) findViewById(R.id.redBox);
//        greenBox = (CheckBox) findViewById(R.id.greenBox);
//        blueBox = (CheckBox) findViewById(R.id.blueBox);
//        textView = (TextView) findViewById(R.id.textView);
//        editView = (EditText) findViewById(R.id.editView);
//        final SharedPreferences settings = getSharedPreferences("W7_P2", MODE_PRIVATE);
//        //The second parameter of settings.getString is the default value,
//        // eg, if the value doesn't exist.
//        textView.setText(settings.getString("textView", "<missing>"));
//        editView.setText(settings.getString("editView", "<missing>"));
//        boolean redChecked = settings.getBoolean("redBox", false);
//        boolean greenChecked = settings.getBoolean("greenBox", false);
//        boolean blueChecked = settings.getBoolean("blueBox", false);
//        redBox.setChecked(redChecked);
//        if(redBox.isChecked()){
//            editView.setBackgroundColor(Color.parseColor("red"));
//            textView.setBackgroundColor(Color.parseColor("red"));
//        }
//        greenBox.setChecked(greenChecked);
//        if(greenBox.isChecked()){
//            editView.setBackgroundColor(Color.parseColor("green"));
//            textView.setBackgroundColor(Color.parseColor("green"));
//        }
//        blueBox.setChecked(blueChecked);
//        if(blueBox.isChecked()){
//            editView.setBackgroundColor(Color.parseColor("blue"));
//            textView.setBackgroundColor(Color.parseColor("blue"));
//        }
//
//        redBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                SharedPreferences.Editor editor = settings.edit();
//
//                if(redBox.isChecked()){
//                    editView.setText("Red");
//                    textView.setText("Red");
//                    editView.setBackgroundColor(Color.parseColor("red"));
//                    textView.setBackgroundColor(Color.parseColor("red"));
//                }
//
//                editor.putBoolean("redBox", redBox.isChecked());
//                editor.putString("textView", textView.getText().toString());
//                editor.putString("editView", editView.getText().toString());
//                editor.commit();
//            }
//        });
//        greenBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                SharedPreferences.Editor editor = settings.edit();
//
//                if(greenBox.isChecked()) {
//                    editView.setText("Green");
//                    textView.setText("Green");
//                    editView.setBackgroundColor(Color.parseColor("green"));
//                    textView.setBackgroundColor(Color.parseColor("green"));
//                }
//                editor.putBoolean("greenBox", greenBox.isChecked());
//                editor.putString("textView", textView.getText().toString());
//                editor.putString("editView", editView.getText().toString());
//                editor.commit();
//
//            }
//        });
//        blueBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//                SharedPreferences.Editor editor = settings.edit();
//
//                if(blueBox.isChecked()){
//                    editView.setText("Blue");
//                    textView.setText("Blue");
//                    editView.setBackgroundColor(Color.parseColor("blue"));
//                    textView.setBackgroundColor(Color.parseColor("blue"));
//                }
//                editor.putBoolean("blueBox", blueBox.isChecked());
//                editor.putString("textView", textView.getText().toString());
//                editor.putString("editView", editView.getText().toString());
//                editor.commit();
//
//            }
//        });
//
//    }
    // Uncomment to use Bundle to restore the information

//    protected void onSaveInstanceState(Bundle outState){
//        super.onSaveInstanceState(outState);
//        outState.putString("textView", textView.getText().toString());
//        outState.putString("editView", editView.getText().toString());
//    }
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        String textV = savedInstanceState.getString("textView");
//        String editV = savedInstanceState.getString("editView)");
//        textView.setText(textV);
//        editView.setText(editV);
//    }

}
