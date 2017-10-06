package com.example.alexrosenfeld10.c4_28;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UnitConversion extends AppCompatActivity {

    private Button btnConvert;
    private EditText edtInput;
    private TextView txtResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_conversion);


        btnConvert = (Button) findViewById(R.id.btnConvert);
        edtInput = (EditText) findViewById(R.id.edtInput);
        txtResult = (TextView) findViewById(R.id.txtResult);


        btnConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String conversion;

                try {
                    conversion = getIntent().getExtras().getString("conversion");
                } catch (NullPointerException e) {
                    System.err.println("didn't receive a direction for conversion");
                    return;
                }

                Double num = Double.parseDouble(edtInput.getText().toString());

                switch (conversion) {
                    case "kmToMiles":
                        num = num * 0.621371;
                        break;
                    case "milesToKm":
                        num = num * 1.60934;
                        break;
                }

                txtResult.setText(num.toString());
            }
        });
    }
}
