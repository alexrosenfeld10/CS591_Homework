package com.example.alexrosenfeld10.fourthgradeflashcard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUser;
    private EditText edtPass;
    private Button btnSubmit;

    private String userName = "williamthehalo";
    private String password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);

        edtUser = (EditText) findViewById(R.id.edtUser);
        edtPass = (EditText) findViewById(R.id.edtPass);
        btnSubmit = (Button) findViewById(R.id.btnSubmitLogin);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtUser.getText().toString().equals(userName) && edtPass.getText().toString().equals(password)) {
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    i.putExtra("userName", userName);
                    startActivity(i);
                }
            }
        });
    }
}