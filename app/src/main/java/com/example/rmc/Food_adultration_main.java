package com.example.rmc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

            fillRecyclerWithLanguate("milk_products");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void fillRecyclerWithLanguate(String lang) {
        try {
            milk_products = json_milk_products.getJSONArray(lang);
            RecyclerView fillMilkProducts = findViewById(R.id.showMilkProducts);
            MilkProductsRecycler adapRecycler = new MilkProductsRecycler(getApplicationContext(), milk_products);
            fillMilkProducts.setAdapter(adapRecycler);
            fillMilkProducts.setLayoutManager(new LinearLayoutManager(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.language, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.english:
                fillRecyclerWithLanguate("milk_products");
                return true;
            case R.id.hindi:
                fillRecyclerWithLanguate("milk_products-HN");
                return true;
            case R.id.gujarati:
                fillRecyclerWithLanguate("milk_products-GJ");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
