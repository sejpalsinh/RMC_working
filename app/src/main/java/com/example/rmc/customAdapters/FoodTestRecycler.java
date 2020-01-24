package com.example.rmc.customAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rmc.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

public class FoodTestRecycler extends RecyclerView.Adapter<FoodTestRecycler.MenuFoodFragmentViewHolder> {

    JSONArray modelList;
    Context context;
    OnMenuTestListener onMenuTestListener;

    public FoodTestRecycler(Context context, JSONArray modelList, OnMenuTestListener onMenuTestListener) {
        this.modelList = modelList;
        this.context = context;
        this.onMenuTestListener = onMenuTestListener;
    }

    public int getClickedTestID(int position){
        try {
            return modelList.getJSONObject(position).getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }


    @NonNull
    @Override
    public MenuFoodFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_menu_food, parent, false);
        MenuFoodFragmentViewHolder viewHolder = new MenuFoodFragmentViewHolder(view, onMenuTestListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodTestRecycler.MenuFoodFragmentViewHolder holder, int position) {
        try {
            holder.testTitle.setText(modelList.getJSONObject(position).getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return modelList.length();
    }

    public class MenuFoodFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView testTitle;
        LinearLayout linearLayout;
        OnMenuTestListener onMenuTestListener;

        public MenuFoodFragmentViewHolder(@NonNull View itemView, OnMenuTestListener onMenuTestListener) {
            super(itemView);
            testTitle = itemView.findViewById(R.id.testTitle);
            linearLayout = itemView.findViewById(R.id.parent_one_menu_food);
            this.onMenuTestListener = onMenuTestListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onMenuTestListener.OnMenuTestClickListener(getAdapterPosition());

        }
    }

    public interface OnMenuTestListener{
        void OnMenuTestClickListener(int position);
    }
}
