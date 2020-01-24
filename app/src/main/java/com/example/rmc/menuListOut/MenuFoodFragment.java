package com.example.rmc.menuListOut;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmc.OpenJSON;
import com.example.rmc.R;
import com.example.rmc.customAdapters.FoodTestModel;
import com.example.rmc.customAdapters.FoodTestRecycler;
import com.example.rmc.menus.OneTest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuFoodFragment extends Fragment implements FoodTestRecycler.OnMenuTestListener{

    JSONObject json_menu_all;
    JSONArray menuList;

    RecyclerView recyclerView;
    List<FoodTestModel> modelList;
    FoodTestRecycler foodTestRecycler;

    String menuType;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.language, menu);
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.english:
                fillMenuFoodFragment(menuType);
                break;
            case R.id.hindi:
                fillMenuFoodFragment(menuType+"-HN");
                break;
            case R.id.gujarati:
                fillMenuFoodFragment(menuType+"-GJ");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list_food, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        try {
            json_menu_all = new JSONObject(OpenJSON.readJSONFromAsset(getContext(), "milk_products.json"));

            Log.i("json_menu_all_list", json_menu_all.toString());

            menuType = getArguments().getString("menuType");

            fillMenuFoodFragment(menuType);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void fillMenuFoodFragment(String menuType) {
        Log.i("menuType", menuType);

        try {
            menuList = json_menu_all.getJSONArray(menuType);
            Log.i("json_menu_all_list", menuList.toString());

            recyclerView = getView().findViewById(R.id.fragment_menu_food_recycler);

            foodTestRecycler = new FoodTestRecycler(getContext(), menuList, this);

            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(foodTestRecycler);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void OnMenuTestClickListener(int position) {
        int id = foodTestRecycler.getClickedTestID(position);
        Log.i("listID", String.valueOf(id));
        Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();

        Fragment moveFragment = new OneTest();

        for(int i = 0 ; i < menuList.length(); i++){
            try {
                JSONObject object = menuList.getJSONObject(i);
                if(object.getInt("id") == id){
                    Bundle bundle = new Bundle();
                    bundle.putString("title", object.getString("title"));

                    JSONArray jsonSteps = object.getJSONArray("steps");
                    String[] steps = new String[jsonSteps.length()];

                    for(int one_step = 0; one_step < jsonSteps.length(); one_step++){
                        steps[one_step] = jsonSteps.getString(one_step);
                        Log.i("Steps ", jsonSteps.getString(one_step));
                    }


                    bundle.putStringArray("steps", steps);
                    bundle.putString("imageSuccess", object.getString("imageSuccess"));


                    moveFragment.setArguments(bundle);

//                    Toast.makeText(getContext(), steps[0], Toast.LENGTH_SHORT).show();
//                    Log.i("Steps", steps.toString());

                    break;

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer,
                moveFragment)
                .commit();

    }
}
