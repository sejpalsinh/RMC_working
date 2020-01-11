package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goto_1_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Food_adultration_main.class));
    }

    public void goto_2_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Water_reuseguide_main.class));
    }

    public void goto_3_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Waste_segregation_main.class));
    }

    public void goto_4_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Waste_pickupreport_main.class));
    }

    public void goto_5_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Dirty_toiletreport_main.class));
    }

    public void goto_6_activity(View view) {
        startActivity(new Intent(getApplicationContext(),Injured_animalreport_main.class));
    }
}
