package com.example.alexrosenfeld10.w6_p1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//this will get inflated down below.

/**
 * A simple {@link Fragment} subclass.
 */
public class BottomFragment extends Fragment {

    private TextView txtFunnyMessage;

    public BottomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_blank, container, false);  //separate me from return statement.
        txtFunnyMessage = (TextView)view.findViewById(R.id.txtFunnyMessage);      //need a chance to do this other stuff,
        return view;
    }

    //Receiving Team
    //It is best practice that this should be accessed via the main activity, not other fragments.
    public void setFunnyMessage(String msg){
        txtFunnyMessage.setText(msg);
    }

}
