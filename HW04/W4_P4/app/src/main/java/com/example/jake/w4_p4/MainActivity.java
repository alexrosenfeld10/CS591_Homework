package com.example.jake.w4_p4;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
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
    }


}
