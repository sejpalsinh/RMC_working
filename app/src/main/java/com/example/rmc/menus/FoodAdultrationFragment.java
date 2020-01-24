package com.example.rmc.menus;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmc.MenuFoodRecycler;
import com.example.rmc.OpenJSON;
import com.example.rmc.R;
import com.example.rmc.menuListOut.MenuFoodFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FoodAdultrationFragment extends Fragment implements MenuFoodRecycler.OnMenuFoodListener{

    JSONObject json_menu_all;
    JSONArray menuList;
    MenuFoodRecycler menuFoodRecycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_food_adulteration, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            json_menu_all = new JSONObject(OpenJSON.readJSONFromAsset(getContext(), "milk_products.json"));

            Log.i("json_menu_all", json_menu_all.toString());
            fillMenu("menuFoodAdulteration");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Toast.makeText(getContext(), "Hey", Toast.LENGTH_SHORT).show();
    }

    private void fillMenu(String menu) {
        try {
            menuList = json_menu_all.getJSONArray(menu);
            Log.i("json_menu_all", menuList.toString());
            RecyclerView menuRecyclerView = getView().findViewById(R.id.fragment_menu_food_adulteration);
            menuFoodRecycler = new MenuFoodRecycler(getContext(), menuList ,this);
            menuRecyclerView.setAdapter(menuFoodRecycler);
            menuRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void OnMenuFoodClickListener(int position) {
        String src = menuFoodRecycler.getClickedMenuName(position);
        Toast.makeText(getContext(), src, Toast.LENGTH_SHORT).show();
        Fragment moveFragment = new MenuFoodFragment();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                moveFragment).commit();
        Log.i("mnuList", src);
    }
}
