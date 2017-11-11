package com.example.alexrosenfeld10.w8_p1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateUserActivity extends AppCompatActivity {

    private EditText edtNewUser;
    private EditText edtNewPass;
    private Button btnSubmitNewUser;

    //TODO remove the user/pass here
    private String userName = "williamthehalo";
    private String password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        edtNewUser = findViewById(R.id.edtNewUser);
        edtNewPass = findViewById(R.id.edtNewPass);
        btnSubmitNewUser = findViewById(R.id.btnSubmitNewUser);

        //TODO hook into database, create user (or warn using toast if already existing)
        btnSubmitNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtNewUser.getText().toString().equals(userName) && edtNewPass.getText().toString().equals(password)) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("userName", userName);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}
