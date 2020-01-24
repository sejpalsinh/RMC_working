package com.example.rmc.bottom;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rmc.Photobased;
import com.example.rmc.R;
import com.example.rmc.menus.FoodAdultrationFragment;

public class HomeFragment extends Fragment {
    Button food_adultration;
    Button button4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_home, container, false);
        food_adultration = view.findViewById(R.id.food_adultration);
        food_adultration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "hello", Toast.LENGTH_SHORT).show();
                Fragment moveFragment = new FoodAdultrationFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,
                        moveFragment).commit();

            }
        });

        button4 = view.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), Photobased.class);
                startActivity(i);
            }
        });



        return view;
    }
}
