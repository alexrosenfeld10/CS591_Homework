package com.example.jake.w4_p4;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LettersFragment extends Fragment {

    private LinearLayout letterRow1, letterRow2, letterRow3, letterRow4;

    private char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static Button[] letterButtons = new Button[26];
    HangmanListener HL;

    public LettersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_letters, parent, false);
        letterRow1 = (LinearLayout) view.findViewById(R.id.letterRow1);
        letterRow2 = (LinearLayout) view.findViewById(R.id.letterRow2);
        letterRow3 = (LinearLayout) view.findViewById(R.id.letterRow3);
        letterRow4 = (LinearLayout) view.findViewById(R.id.letterRow4);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        HL = (HangmanListener) context;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int letterCount = 0;

        for (final char letter : letters) {
            final Button b = new Button(getContext());
            b.setTag(String.valueOf(letter));
            b.setSaveEnabled(true);

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
            p.width = 120;
            p.weight = 0f;

            b.setLayoutParams(p);
            b.setText(String.valueOf(letter));

            View.OnClickListener letterClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    HL.processGuess(letter);
                    b.setEnabled(false);
                }
            };
            b.setOnClickListener(letterClickListener);

            if (letterCount < 7) {
                letterRow1.addView(b);
            } else if (letterCount < 14) {
                letterRow2.addView(b);
            } else if (letterCount < 21) {
                letterRow3.addView(b);
            } else {
                letterRow4.addView(b);
            }
            letterButtons[letterCount] = b;
            letterCount++;
        }

        if (savedInstanceState != null) {
            for (Button b : letterButtons) {
                b.setEnabled(savedInstanceState.getBoolean((String) b.getTag()));
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for (Button b : letterButtons) {
            outState.putBoolean((String) b.getTag(), b.isEnabled());
        }
    }

    public void resetButtons() {
        for (Button b : letterButtons) {
            if (b != null) {
                b.setEnabled(true);
            }
        }
    }

    public void disableButtons() {
        for (Button b : letterButtons) {
            if (b != null) {
                b.setEnabled(false);
            }
        }
    }
}
