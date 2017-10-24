package com.example.megan.c9_p20;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//this will get inflated down below.

/**
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment {

    private Button light;

    public LightFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.light_layout, container, false);
        light = (Button) view.findViewById(R.id.btnLight);
        light.setBackgroundColor(Color.RED);
        return view;
    }

    public void setLight() {
        Drawable buttonBackground = light.getBackground();
        int color = ((ColorDrawable) buttonBackground).getColor();
        switch (color) {
            case Color.RED:
                light.setBackgroundColor(Color.GREEN);
                break;
            case Color.YELLOW:
                light.setBackgroundColor(Color.RED);
                break;
            case Color.GREEN:
                light.setBackgroundColor(Color.YELLOW);
                break;
        }
    }
}
