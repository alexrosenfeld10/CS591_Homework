package com.example.megan.c9_p20;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */

//This will get inflated up top.
public class ControlFragment extends Fragment {

    private Button btnChangeLight;

    public ControlFragment() {  //todo, why?
        // Required empty public constructor
    }

    public interface ControlFragmentListener {            // interface definition.
        public void changeLight();
    }

    ControlFragmentListener CFL;  //Future reference to an object that implements ControlFragmentListener

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        CFL = (ControlFragmentListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.control_layout, container, false);

        //so we can refer to the views objects before passing view to Activity.
        btnChangeLight = (Button) view.findViewById(R.id.btnChangeLight);

        btnChangeLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CFL.changeLight();  //CFL is a handle to our MainActivity, we are sending it our message text.
            }
        });

        return view;
    }
}
