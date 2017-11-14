package com.example.jake.w4_p4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class HintFragment extends Fragment {

    private TextView hintView;
    private Button newGameButton;

    private static String hint;
    HangmanListener HL;

    public HintFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_hint, parent, false);
        hintView = (TextView) view.findViewById(R.id.hintView);
        newGameButton = (Button) view.findViewById(R.id.newGameButton);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HL.setUpNewGame();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        HL = (HangmanListener) context;
    }

    public void setHint(String hint) {
        hintView.setText(hint);
    }
}