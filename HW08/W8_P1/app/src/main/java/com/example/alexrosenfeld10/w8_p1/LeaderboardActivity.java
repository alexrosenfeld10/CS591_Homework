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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        txtName1 = findViewById(R.id.txtName1);
        txtName2 = findViewById(R.id.txtName2);
        txtName3 = findViewById(R.id.txtName3);
        txtName4 = findViewById(R.id.txtName4);
        txtName5 = findViewById(R.id.txtName5);

        txtScore1 = findViewById(R.id.txtScore1);
        txtScore2 = findViewById(R.id.txtScore2);
        txtScore3 = findViewById(R.id.txtScore3);
        txtScore4 = findViewById(R.id.txtScore4);
        txtScore5 = findViewById(R.id.txtScore5);

        getData();
    }

    private void getData(){
        //TODO connect to database and get data
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference users = database.getReference().child("Users/");
        DatabaseReference tests = database.getReference().child("Tests/");
        Query queryRef = tests.orderByChild("score");
        queryRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int x = 4;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
