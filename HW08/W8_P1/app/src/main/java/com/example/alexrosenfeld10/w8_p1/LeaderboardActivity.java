package com.example.alexrosenfeld10.w8_p1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardActivity extends AppCompatActivity {

    //TODO define needed view references
    TextView txtName1;
    TextView txtName2;
    TextView txtName3;
    TextView txtName4;
    TextView txtName5;
    TextView txtScore1;
    TextView txtScore2;
    TextView txtScore3;
    TextView txtScore4;
    TextView txtScore5;

    TextView[] names = new TextView[5];
    TextView[] scores = new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        txtName1 = (TextView) findViewById(R.id.txtName1);
        txtName2 = (TextView)findViewById(R.id.txtName2);
        txtName3 = (TextView)findViewById(R.id.txtName3);
        txtName4 = (TextView)findViewById(R.id.txtName4);
        txtName5 = (TextView)findViewById(R.id.txtName5);

        txtScore1 = (TextView)findViewById(R.id.txtScore1);
        txtScore2 = (TextView)findViewById(R.id.txtScore2);
        txtScore3 = (TextView) findViewById(R.id.txtScore3);
        txtScore4 = (TextView)findViewById(R.id.txtScore4);
        txtScore5 = (TextView)findViewById(R.id.txtScore5);

        names = new TextView[]{txtName1, txtName2, txtName3, txtName4, txtName5};
        scores = new TextView[]{txtScore1, txtScore2, txtScore3, txtScore4, txtScore5};

        getData();
    }

    private void getData() {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference().child("Users/");
        DatabaseReference tests = database.getReference().child("Tests/");
        Query queryRef = tests.orderByChild("score");

        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Test> data = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    data.add(snapshot.getValue(Test.class));
                }

                for (int i = 0; i < 5 && i >= data.size(); i++) {
                    Test currentTest = data.get(data.size() - i - 1);
                    names[i].setText(currentTest.getFullName());
                    scores[i].setText(Integer.toString(currentTest.getScore()));
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
