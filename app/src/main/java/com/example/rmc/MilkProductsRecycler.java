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

public class MilkProductsRecycler extends RecyclerView.Adapter<MilkProductsRecycler.ViewHolder> {
    JSONArray milk_products;
    Context context;

    public MilkProductsRecycler(Context context, JSONArray milk_products) {
        this.milk_products = milk_products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_milk_product, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        try {
            JSONObject one_milk = milk_products.getJSONObject(position);
            String s_name;
            int resID ;

            holder.milk_title.setText(one_milk.getString("title"));
            holder.milk_content.setText(one_milk.getString("steps"));

            s_name = one_milk.getString("imageSuccess");
            resID = context.getResources().getIdentifier(s_name,
                    "drawable", context.getPackageName());
            holder.imageSuccess.setImageResource(resID);

            s_name = one_milk.getString("imageSuccess");
            resID = context.getResources().getIdentifier(s_name,
                    "drawable", context.getPackageName());
            holder.imageSuccess.setImageResource(resID);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return milk_products.length();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView milk_title, testingMethod, milk_content;
        ImageView imageSuccess, imageFailure;
        LinearLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            milk_title = itemView.findViewById(R.id.milk_title);
            testingMethod = itemView.findViewById(R.id.testingMethod);
            milk_content = itemView.findViewById(R.id.milk_content);
            imageSuccess = itemView.findViewById(R.id.imageSuccess);
            imageFailure = itemView.findViewById(R.id.imageFailure);
            parentLayout = itemView.findViewById(R.id.parent_one_milk_product);

        }

    }
}
