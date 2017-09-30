package com.example.alexrosenfeld10.c4_28;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnKmToMiles;
    private Button btnMilesToKm;

    private Intent unitConversionActivity;

    private View.OnClickListener btnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        unitConversionActivity = new Intent(getApplicationContext(), UnitConversion.class);

        btnKmToMiles = (Button) findViewById(R.id.btnKmToMiles);
        btnMilesToKm = (Button) findViewById(R.id.btnMilesToKm);

        btnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btnKmToMiles:
                        unitConversionActivity.putExtra("conversion", "kmToMiles");
                        break;
                    case R.id.btnMilesToKm:
                        unitConversionActivity.putExtra("conversion", "milesToKm");
                        break;
                }
                startActivity(unitConversionActivity);
            }
        };

        btnKmToMiles.setOnClickListener(btnClickListener);
        btnMilesToKm.setOnClickListener(btnClickListener);
    }
}
