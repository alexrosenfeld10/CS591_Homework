package com.example.alexrosenfeld10.w8_p1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUser;
    private EditText edtPass;
    private Button btnSubmitLogin;
    private Button btnCreateUser;

    //TODO remove these
    private String userName = "williamthehalo";
    private String password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUser = findViewById(R.id.edtUser);
        edtPass = findViewById(R.id.edtPass);
        btnSubmitLogin = findViewById(R.id.btnSubmitLogin);
        btnCreateUser = findViewById(R.id.btnCreateUser);

        //TODO hook into database and authenticate the user
        btnSubmitLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUser.getText().toString().equals(userName) && edtPass.getText().toString().equals(password)) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("userName", userName);
                    startActivity(i);
                }
            }
        });

        btnCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), CreateUserActivity.class);
                startActivity(i);
            }
        });
    }
}
