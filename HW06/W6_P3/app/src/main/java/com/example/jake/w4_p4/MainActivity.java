package com.example.jake.w4_p4;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements HangmanListener {

    private static final String logFlag = "W6_P3";  // Marker for logging events from the app.

    GameFragment gameFragment;
    HintFragment hintFragment;
    LettersFragment lettersFragment;
    private static String wordToSolve, wordToDisplay, hint;
    private static boolean firstGame = true;
    private static int lives = 6;
    private static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(logFlag, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameFragment = (GameFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_game);
        hintFragment = (HintFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_hint);
        lettersFragment = (LettersFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_letters);

        if (savedInstanceState != null) {
            wordToSolve = savedInstanceState.getString("WORD_SOLVED_KEY");
            wordToDisplay = savedInstanceState.getString("WORD_DISPLAY_KEY");
            gameFragment.setDisplayWord(wordToDisplay);
            hint = savedInstanceState.getString("HINT_KEY");
            hintFragment.setHint("HINT: " + hint);
            firstGame = savedInstanceState.getBoolean("FIRST_GAME");
            lives = savedInstanceState.getInt("LIVES_LEFT");
            gameFragment.setHangmanImage(lives);
        }

        if (firstGame) {
            setUpNewGame();
            firstGame = false;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(logFlag, "onSaveInstanceState");
        outState.putString("WORD_SOLVED_KEY", wordToSolve);
        outState.putString("WORD_DISPLAY_KEY", wordToDisplay);
        outState.putString("HINT_KEY", hint);
        outState.putBoolean("FIRST_GAME", firstGame);
        outState.putInt("LIVES_LEFT", lives);
        super.onSaveInstanceState(outState);
    }

    public void processGuess(char letter) {
        String response;
        boolean gameEnd = false;
        boolean correct = false;
        for (int i = 0; i < wordToSolve.length(); i++) {
            if (wordToSolve.charAt(i) == letter) {
                char[] wordArray = wordToDisplay.toCharArray();
                wordArray[i * 2] = letter;
                wordToDisplay = String.valueOf(wordArray);
                gameFragment.setDisplayWord(wordToDisplay);
                correct = true;
            }
        }
        if (correct) {
            if (wordToDisplay.indexOf('_') == -1) {
                response = "Good job, you guessed the word!";
                gameEnd = true;
            } else {
                response = "Correct!";
            }
        } else {
            lives--;
            if (lives == 0) {
                response = "No more guesses. Game over!";
                gameEnd = true;
            } else {
                response = "Wrong! Try again.";
            }
            gameFragment.setHangmanImage(lives);
        }

        if (gameEnd) {
            lettersFragment.disableButtons();
        }
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT);
        toast.show();
    }

    public void setUpNewGame() {
        lettersFragment.resetButtons();

        TypedArray words = getResources().obtainTypedArray(R.array.hangman_words);
        int index = (int) (Math.random() * words.length());
        int arrayId = words.getResourceId(index, R.array.word0);
        String[] word = getResources().getStringArray(arrayId);

        wordToSolve = word[0];
        hint = word[1];
        wordToDisplay = new String(new char[wordToSolve.length()]).replace("\0", "_");
        wordToDisplay = wordToDisplay.replace("", " ").trim();
        gameFragment.setDisplayWord(wordToDisplay);

        hintFragment.setHint("HINT: " + word[1]);

        lives = 6;
        gameFragment.setHangmanImage(lives);
    }
}
