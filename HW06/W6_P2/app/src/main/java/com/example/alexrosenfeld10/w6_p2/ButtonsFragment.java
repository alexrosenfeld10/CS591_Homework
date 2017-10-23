package com.example.alexrosenfeld10.w6_p2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class ButtonsFragment extends Fragment {

    private Button btnLeft;
    private Button btnRight;

    public ButtonsFragment() {
        // Required empty public constructor
    }

    public interface ButtonsFragmentListener {
        public void sendMessage(String msg);
    }

    ButtonsFragmentListener BFL;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        BFL = (ButtonsFragmentListener) context;  //context is a handle to the main activity, let's bind it to our interface.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_buttons, container, false);   //MUST HAPPEN FIRST, otherwise components don't exist.

        btnRight = (Button) v.findViewById(R.id.btnRight);
        btnLeft = (Button) v.findViewById(R.id.btnLeft);

        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BFL.sendMessage("left");
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BFL.sendMessage("right");
            }
        });

        return v;   //must happen last, it is a return statement after all, it can't happen sooner!
    }
}