package com.example.jake.w4_p4;

        import android.content.DialogInterface;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.LinearLayout;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String logFlag = "W4_P4";  // Marker for logging events from the app.

    private ImageView hangmanView;
    private TextView wordView, hintView;
    private LinearLayout letterRow1, letterRow2, letterRow3, letterRow4;
    private Button newGameButton;

    private char[] letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private Button[] letterButtons = new Button[26];
    private boolean firstGame = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up view references
        hangmanView = (ImageView) findViewById(R.id.hangmanView);
        wordView = (TextView) findViewById(R.id.wordView);
        hintView = (TextView) findViewById(R.id.hintView);
        letterRow1 = (LinearLayout) findViewById(R.id.letterRow1);
        letterRow2 = (LinearLayout) findViewById(R.id.letterRow2);
        letterRow3 = (LinearLayout) findViewById(R.id.letterRow3);
        letterRow4 = (LinearLayout) findViewById(R.id.letterRow4);
        newGameButton = (Button) findViewById(R.id.newGameButton);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpNewGame();
            }
        });

        if (firstGame) {
            int letterCount = 0;
            for (char letter : letters) {
                final Button b = new Button(this);
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
                        b.getText();
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
            //setUpNewGame();
            firstGame = false;
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (Button b : letterButtons) {
            b.setEnabled(savedInstanceState.getBoolean((String) b.getTag()));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        for (Button b : letterButtons) {
            outState.putBoolean((String) b.getTag(), b.isEnabled());
        }
        super.onSaveInstanceState(outState);
    }

    private void setUpNewGame() {
        for (Button b : letterButtons) {
            b.setEnabled(true);
        }
    }

}
