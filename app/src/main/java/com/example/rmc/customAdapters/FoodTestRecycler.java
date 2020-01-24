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

import java.util.List;

public class FoodTestRecycler extends RecyclerView.Adapter<FoodTestRecycler.MenuFoodFragmentViewHolder> {

    List<FoodTestModel> modelList;
    Context context;
    OnMenuTestListener onMenuTestListener;

    public FoodTestRecycler(Context context, List<FoodTestModel> modelList, OnMenuTestListener onMenuTestListener) {
        this.modelList = modelList;
        this.context = context;
        this.onMenuTestListener = onMenuTestListener;
    }

    public int getClickedTestID(int position){
        return modelList.get(position).getId();
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
        holder.testTitle.setText(modelList.get(position).getTestName());

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class MenuFoodFragmentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView testTitle;
        LinearLayout linearLayout;
        OnMenuTestListener onMenuTestListener;

        public MenuFoodFragmentViewHolder(@NonNull View itemView, OnMenuTestListener onMenuTestListener) {
            super(itemView);
            testTitle = itemView.findViewById(R.id.testTitle);
            linearLayout = itemView.findViewById(R.id.parent_one_menu_food);

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
