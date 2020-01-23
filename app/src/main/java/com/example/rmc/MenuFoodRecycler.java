package com.example.rmc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MenuFoodRecycler extends RecyclerView.Adapter<MenuFoodRecycler.ViewHolder> {
    JSONArray menuList;
    Context context;
    OnMenuFoodListener onMenuFoodListener;


    public MenuFoodRecycler(Context context, JSONArray menuList, OnMenuFoodListener onMenuFoodListener) {
        this.menuList = menuList;
        this.context = context;
        this.onMenuFoodListener = onMenuFoodListener;
    }


    public String getClickedMenuName(int position){
        try {
            return menuList.getJSONObject(position).getString("src");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_food_adulteration, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, onMenuFoodListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject one_menu = menuList.getJSONObject(position);
            holder.titleLeft.setText(one_menu.getString("title"));

            String s_name = one_menu.getString("img");
            int resID = context.getResources().getIdentifier(s_name,
                    "drawable", context.getPackageName());
            holder.foodLeft.setImageResource(resID);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return menuList.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView titleLeft;
        ImageView foodLeft;
        LinearLayout parentLayout;
        OnMenuFoodListener onMenuFoodListener;

        public ViewHolder(@NonNull View itemView , OnMenuFoodListener onMenuFoodListener) {
            super(itemView);
            titleLeft = itemView.findViewById(R.id.titleLeft);
            foodLeft = itemView.findViewById(R.id.foodMenuLeft);
            parentLayout = itemView.findViewById(R.id.parent_menu_food_adulteration);
            this.onMenuFoodListener = onMenuFoodListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onMenuFoodListener.OnMenuFoodClickListener(getAdapterPosition());
        }


    }
    public interface OnMenuFoodListener {
        void OnMenuFoodClickListener(int position);
    }
}
