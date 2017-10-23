package com.example.jake.w4_p4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class GameFragment extends Fragment {

    private ImageView hangmanView;
    private TextView wordView;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_game, parent, false);
        hangmanView = (ImageView) view.findViewById(R.id.hangmanView);
        wordView = (TextView) view.findViewById(R.id.wordView);
        return view;
    }

    public void setHangmanImage(int lives) {
        switch (lives) {
            case 6:
                hangmanView.setImageResource(R.drawable.hangman_0);
                break;
            case 5:
                hangmanView.setImageResource(R.drawable.hangman_1);
                break;
            case 4:
                hangmanView.setImageResource(R.drawable.hangman_2);
                break;
            case 3:
                hangmanView.setImageResource(R.drawable.hangman_3);
                break;
            case 2:
                hangmanView.setImageResource(R.drawable.hangman_4);
                break;
            case 1:
                hangmanView.setImageResource(R.drawable.hangman_5);
                break;
            case 0:
                hangmanView.setImageResource(R.drawable.hangman_6);
                break;
            default:
                hangmanView.setImageResource(R.drawable.hangman_0);
                break;
        }
    }

    public void setDisplayWord(String word) {
        wordView.setText(word);
    }
}
