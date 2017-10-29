package com.example.tkixi.w7_p2;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class SharedFrag extends Fragment {
    private CheckBox redBox, greenBox, blueBox;
    private TextView textView;
    private EditText editView;
    android.support.v4.app.FragmentManager fm;

    public SharedFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_frag, container, false);
        // Inflate the layout for this fragment
        redBox = (CheckBox) v.findViewById(R.id.redBox);
        greenBox = (CheckBox) v.findViewById(R.id.greenBox);
        blueBox = (CheckBox) v.findViewById(R.id.blueBox);
        textView = (TextView) v.findViewById(R.id.textView);
        editView = (EditText) v.findViewById(R.id.editView);
        final SharedPreferences settings = this.getActivity().getSharedPreferences("W7_P2", MODE_PRIVATE);
        //The second parameter of settings.getString is the default value,
        // eg, if the value doesn't exist.
        textView.setText(settings.getString("textView", "<missing>"));
        editView.setText(settings.getString("editView", "<missing>"));
        boolean redChecked = settings.getBoolean("redBox", false);
        boolean greenChecked = settings.getBoolean("greenBox", false);
        boolean blueChecked = settings.getBoolean("blueBox", false);
        // Checks the SharedPreferences
        redBox.setChecked(redChecked);
        if(redBox.isChecked()){
            editView.setBackgroundColor(Color.parseColor("red"));
            textView.setBackgroundColor(Color.parseColor("red"));
        }
        greenBox.setChecked(greenChecked);
        if(greenBox.isChecked()){
            editView.setBackgroundColor(Color.parseColor("green"));
            textView.setBackgroundColor(Color.parseColor("green"));
        }
        blueBox.setChecked(blueChecked);
        if(blueBox.isChecked()){
            editView.setBackgroundColor(Color.parseColor("blue"));
            textView.setBackgroundColor(Color.parseColor("blue"));
        }

        redBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = settings.edit();

                if(redBox.isChecked()){
                    editView.setText("Red");
                    textView.setText("Red");
                    editView.setBackgroundColor(Color.parseColor("red"));
                    textView.setBackgroundColor(Color.parseColor("red"));
                }

                editor.putBoolean("redBox", redBox.isChecked());
                editor.putString("textView", textView.getText().toString());
                editor.putString("editView", editView.getText().toString());
                editor.commit();
            }
        });
        greenBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = settings.edit();

                if(greenBox.isChecked()) {
                    editView.setText("Green");
                    textView.setText("Green");
                    editView.setBackgroundColor(Color.parseColor("green"));
                    textView.setBackgroundColor(Color.parseColor("green"));
                }
                editor.putBoolean("greenBox", greenBox.isChecked());
                editor.putString("textView", textView.getText().toString());
                editor.putString("editView", editView.getText().toString());
                editor.commit();

            }
        });
        blueBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences.Editor editor = settings.edit();

                if(blueBox.isChecked()){
                    editView.setText("Blue");
                    textView.setText("Blue");
                    editView.setBackgroundColor(Color.parseColor("blue"));
                    textView.setBackgroundColor(Color.parseColor("blue"));
                }
                editor.putBoolean("blueBox", blueBox.isChecked());
                editor.putString("textView", textView.getText().toString());
                editor.putString("editView", editView.getText().toString());
                editor.commit();

            }
        });

        return v;
    }

}

