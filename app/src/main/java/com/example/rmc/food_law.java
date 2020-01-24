package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class food_law extends AppCompatActivity {
    WebView w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_law);
        w = findViewById(R.id.wb);
        System.out.println("urururururur");
        w.getSettings().setJavaScriptEnabled(true);
        w.getSettings().setPluginState(WebSettings.PluginState.ON);

        w.loadUrl("https://www.fssai.gov.in/upload/uploadfiles/files/Report_Intern_Anusree_04_10_2019.pdf");
    }
}
