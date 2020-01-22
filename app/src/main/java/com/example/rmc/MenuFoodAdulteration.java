package com.example.rmc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuFoodAdulteration extends AppCompatActivity implements MenuFoodRecycler.OnMenuFoodListener{
    JSONObject json_menu_all;
    JSONArray menuList;
    MenuFoodRecycler menuFoodRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_food_adulteration);

        try {
            json_menu_all = new JSONObject(OpenJSON.readJSONFromAsset(getApplicationContext(), "milk_products.json"));

            fillMenu("menuFoodAdulteration");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void fillMenu(String menu) {
        try {
            menuList = json_menu_all.getJSONArray(menu);
            RecyclerView menuRecyclerView = findViewById(R.id.menu_food_adulteration);
            menuFoodRecycler = new MenuFoodRecycler(getApplicationContext(), menuList ,this);
            menuRecyclerView.setAdapter(menuFoodRecycler);
            menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void OnMenuFoodClickListener(int position) {
        String src = menuFoodRecycler.getClickedMenuName(position);
        Intent i = new Intent(this, Food_adultration_main.class);
        i.putExtra("src", src);
        startActivity(i);
        Log.i("mnuList", src);

    }
}
