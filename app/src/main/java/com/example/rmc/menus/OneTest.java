package com.example.rmc.menus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.rmc.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class OneTest extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_test, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] steps = {"Step 1", "Step 2", "Step 3"};

        final LinearLayout linearLayout = getActivity().findViewById(R.id.fillSteps);

        final List<CheckBox> lstCheckbox = new ArrayList<CheckBox>();

        for(int i = 0; i < steps.length; i++)
        {

            final CheckBox checkBox = new CheckBox(getContext());
            checkBox.setId(i);
            checkBox.setText(steps[i]);
            checkBox.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));

            lstCheckbox.add(checkBox);


        }

        for(int i = 0; i < steps.length - 1; i++)
        {

            final int finalI = i;
            lstCheckbox.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(lstCheckbox.get(finalI).isChecked()){
                        linearLayout.addView(lstCheckbox.get(finalI + 1));
                    }
                }
            });


        }

        linearLayout.addView(lstCheckbox.get(0));

    }
}
