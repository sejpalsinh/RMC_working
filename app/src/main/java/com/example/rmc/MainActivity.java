package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences("ssiprajkot", MODE_PRIVATE);
        editor = preferences.edit();
    }

    public void goto_1_activity(View view) {
        startActivity(new Intent(getApplicationContext(), BottomNavMain.class));
    }


    public void goto_3_activity(View view) {
        startActivity(new Intent(getApplicationContext(),food_complain.class));
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainacty , menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void log_out(MenuItem item) {
        editor.putString("uemail"," ");
        editor.putString("uname"," ");
        editor.putString("unumber"," ");
        editor.commit();
        startActivity(new Intent(getApplicationContext(),UserLogin.class));
        finish();
    }

    public void abt_Us(MenuItem item) {

    }

}
