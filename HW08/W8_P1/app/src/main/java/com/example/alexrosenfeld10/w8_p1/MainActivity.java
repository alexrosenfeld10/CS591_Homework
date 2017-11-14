package com.example.alexrosenfeld10.w8_p1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText edtQuotient;
    private TextView txtDividend;
    private TextView txtDivisor;
    private Button btnLeaderboard;

    private int numCorrect = 0;
    private int numQuestions = 0;
    private String currentTestId;
    private String Uid;
    private String userFullName;

    private Random random = new Random();

    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();

        btnLeaderboard = findViewById(R.id.btnLeaderboard);

        btnLeaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LeaderboardActivity.class);
                startActivity(i);
            }
        });

        String userName;
        String userGUID;
        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString("userName");
        userGUID = bundle.getString("Uid");
        userFullName = bundle.getString("fullName");

        Uid = userGUID;

        Toast.makeText(MainActivity.this, "Welcome " + userName, Toast.LENGTH_LONG).show();

        createAndBindNewTest();

        setupQuestion();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        edtQuotient.setText(savedInstanceState.getString("currentAnswer"));
        numCorrect = savedInstanceState.getInt("numCorrect");
        numQuestions = savedInstanceState.getInt("numQuestions");
        txtDividend.setText(savedInstanceState.getString("dividend"));
        txtDivisor.setText(savedInstanceState.getString("divisor"));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        // Current score, which question, editText
        outState.putString("currentAnswer", edtQuotient.getText().toString());
        outState.putInt("numCorrect", numCorrect);
        outState.putInt("numQuestions", numQuestions);
        outState.putString("dividend", txtDividend.getText().toString());
        outState.putString("divisor", txtDivisor.getText().toString());

        super.onSaveInstanceState(outState);
    }
    /* View editing helper functions */

    public void resetQuestion(View v) {

        checkAnswer();

        numQuestions++;

        if (numQuestions == 10) {
            String message = "You got " + numCorrect + " correct!";
            Toast toast = Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG);
            toast.show();
            numQuestions = 0; // let the user keep going
            numCorrect = 0; // reset correct count

            createAndBindNewTest();

        }

        setupQuestion();
    }

    private void createAndBindNewTest() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tests = database.getReference().child("Tests/");
        DatabaseReference users = database.getReference().child("Users/");

        final String newTestKey = tests.push().getKey();
        Test test = new Test(newTestKey, Calendar.getInstance().getTime().toString(), 0, Uid, new ArrayList<String>(), userFullName);
        currentTestId = newTestKey;
        Map<String, Object> testUpdate = new HashMap<>();
        testUpdate.put(newTestKey, test.toMap());

        tests.updateChildren(testUpdate);
        final DatabaseReference testsRef = users.child(Uid).child("tests");
        testsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<String> tests = dataSnapshot.getValue() == null ? new ArrayList<String>() : (ArrayList<String>) dataSnapshot.getValue();
                tests.add(newTestKey);
                testsRef.setValue(tests);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setupQuestion() {

        /* Find view references */

        edtQuotient = findViewById(R.id.edtQuotient);
        txtDividend = findViewById(R.id.txtDividend);
        txtDivisor = findViewById(R.id.txtDivisor);

        int newDivisor = random.nextInt(50 - 1) + 1;
        txtDivisor.setText(Integer.toString(newDivisor));
        int newDividend = generateDividendFor(newDivisor);
        txtDividend.setText(Integer.toString(newDividend));
        edtQuotient.setText("");
    }

    /* Mathematical helper functions */

    private int generateDividendFor(int divisor) {
        int dividend = divisor;

        // Heuristic approach for getting a new problem
        // If we can't find anything within 500 tries, return dividend = divisor
        // this way, quotient = 1
        for (int i = 0; i < 500; i++) {
            int newDividend = divisor * (random.nextInt(50 - 1) + 1);
            if (newDividend <= 100) {
                return newDividend;
            }
        }

        return dividend;
    }

    private void checkAnswer() {

        int divisor = Integer.parseInt(txtDivisor.getText().toString());
        int dividend = Integer.parseInt(txtDividend.getText().toString());
        int correctAnswer = dividend / divisor;
        boolean correct = false;

        String answer_string = edtQuotient.getText().toString();
        int answer = Integer.parseInt(answer_string);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference tests = database.getReference().child("Tests/");
        DatabaseReference problems = database.getReference().child("IndividualProblemResults/");

        if (answer == correctAnswer) {
            numCorrect++;
            correct = true;

            // Update the score if they got the question correct
            final DatabaseReference currentTestScoreRef = tests.child(currentTestId).child("score");
            currentTestScoreRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    long score = (long) dataSnapshot.getValue(); // getValue returns a long for some reason, needed to cast
                    int intScore = new BigDecimal(score).intValueExact();
                    intScore++; // setValue is async, must increment score first
                    currentTestScoreRef.setValue(intScore);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        final String problemKey = tests.push().getKey();
        Question question = new Question(problemKey, numQuestions, dividend, divisor, "/", correct, currentTestId);
        Map<String, Object> questionUpdate = new HashMap<>();
        questionUpdate.put(problemKey, question.toMap());

        problems.updateChildren(questionUpdate);
        final DatabaseReference testsRef = tests.child(currentTestId).child("questions");
        testsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<String> questions = dataSnapshot.getValue() == null ? new ArrayList<String>() : (ArrayList<String>) dataSnapshot.getValue();
                questions.add(problemKey);
                testsRef.setValue(questions);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}