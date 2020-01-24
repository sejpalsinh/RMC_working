package com.example.rmc.menus;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rmc.R;
import com.example.rmc.food_complain;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class OneTest extends Fragment {

    int counter = 1;
    Button gotoFoodComplaint;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_test, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final TextView stepsCounter;


        final String title = getArguments().getString("title");
        final String[] steps = getArguments().getStringArray("steps");

        TextView oneTestTitle = getView().findViewById(R.id.oneTestTitle);
        oneTestTitle.setText(getArguments().getString("title"));

        String imgSuccess = getArguments().getString("imageSuccess");
        Log.i("imgS", imgSuccess);

        final LinearLayout linearLayout = getActivity().findViewById(R.id.fillSteps);

        final List<CheckBox> lstCheckbox = new ArrayList<CheckBox>();

        for(int i = 0; i < steps.length; i++)
        {

            final CheckBox checkBox = new CheckBox(getContext());
            checkBox.setId(i);
            checkBox.setText(steps[i]);
            checkBox.setTextSize(15);
            checkBox.setPadding(30, 30, 30, 30);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 10, 10, 10);
            checkBox.setLayoutParams(params);

            lstCheckbox.add(checkBox);


        }

        stepsCounter = getView().findViewById(R.id.stepsCounter);
        stepsCounter.setText("Step " + counter + " of " + steps.length);

        lstCheckbox.get(0).setText(steps[0]);

        for(int i = 0; i < steps.length - 1; i++)
        {

            final int finalI = i;
            lstCheckbox.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(lstCheckbox.get(finalI).isChecked()){
                        linearLayout.addView(lstCheckbox.get(finalI + 1));
                        stepsCounter.setText("Step " + ++counter + " of " + steps.length);

                        lstCheckbox.get(finalI).setEnabled(false);
                    }
                }
            });


        }
        linearLayout.addView(lstCheckbox.get(0));



        int resID = getContext().getResources().getIdentifier(imgSuccess,
                "drawable", getContext().getPackageName());

        ImageView imageView = getView().findViewById(R.id.imgSuccess);
        imageView.setImageResource(resID);

        gotoFoodComplaint = getView().findViewById(R.id.button2);
        gotoFoodComplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), food_complain.class);
                startActivity(i);
            }
        });

    }
}
