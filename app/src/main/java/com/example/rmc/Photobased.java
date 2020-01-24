package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Photobased extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photobased);
    }

    public void test_chilipowder(View view) {
        startActivity(new Intent(getApplicationContext(),photo_chili.class));
    }

    public void test_blackppr(View view) {
        startActivity(new Intent(getApplicationContext(),photo_blackppr.class));
    }

    public void test_greenpea(View view) {
        startActivity(new Intent(getApplicationContext(),photo_greenpea.class));
    }

    public void test_potato(View view) {
        startActivity(new Intent(getApplicationContext(),photo_potato.class));
    }

    public void test_okra(View view) {
        startActivity(new Intent(getApplicationContext(),photo_okra.class));
    }
}
