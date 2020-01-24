package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class show_food_image extends AppCompatActivity {
    ImageView imgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food_image);
        imgv = findViewById(R.id.img);
        imgv.setImageBitmap(food_complain.bitmap);
    }
}
