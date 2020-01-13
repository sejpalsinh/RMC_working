package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_screen extends AppCompatActivity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        preferences = getSharedPreferences("ssiprajkot", MODE_PRIVATE);
        editor = preferences.edit();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        {
            Toast.makeText(getApplicationContext(),"Storage Permission Please", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + getPackageName()));
            finish();
            startActivity(intent);
            return;
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(!preferences.getString("uemail", "").equals(""))
                {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
                startActivity(new Intent(getApplicationContext(),UserLogin.class));
                finish();
            }
        },2000);
    }
}
