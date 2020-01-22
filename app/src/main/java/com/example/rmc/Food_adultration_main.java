package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Food_adultration_main extends AppCompatActivity {
    TextView milk_title, milk_content;
    JSONArray milk_products, test1;
    JSONObject json_milk_products;
    ImageView testSuccess, testFailure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_adultration_main);

        milk_title = findViewById(R.id.milk_title);
        milk_content = findViewById(R.id.milk_content);
        testSuccess = findViewById(R.id.imageSuccess);
        testFailure = findViewById(R.id.imageFailure);


        try {
            json_milk_products = new JSONObject(OpenJSON.readJSONFromAsset(getApplicationContext(), "milk_products.json"));
            //milk_content.setText(json_milk_products.toString());

            milk_products = json_milk_products.getJSONArray("milk_products-HN");

            for(int i = 0; i < milk_products.length(); i++){
                JSONObject jsonObject1= milk_products.getJSONObject(i);
                milk_title.setText(jsonObject1.getString("title"));
                milk_content.setText(jsonObject1.getString("steps"));

                String s_name = jsonObject1.getString("imageSuccess");
                int resID = getResources().getIdentifier(s_name,
                        "drawable", getPackageName());
                testSuccess.setImageResource(resID);

                s_name = jsonObject1.getString("imageFailure");
                resID = getResources().getIdentifier(s_name,
                        "drawable", getPackageName());
                testFailure.setImageResource(resID);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
