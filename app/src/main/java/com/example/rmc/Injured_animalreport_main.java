package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Injured_animalreport_main extends AppCompatActivity {

    EditText et_name,et_number,et_location,et_discription;
    public static Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dirty_toiletreport_main);
        et_name = findViewById(R.id.et_name);
        et_number = findViewById(R.id.et_number);
        et_location = findViewById(R.id.et_location);
        et_discription = findViewById(R.id.et_discription);
    }

    public void getTheImage(View view) {
    }

    public void showImg(View view) {
    }

    public void senDataServer(View view) {
    }

    public void cancleForm(View view) {
    }
}
