package com.example.rmc;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiseaseRecycler extends RecyclerView.Adapter<DiseaseRecycler.DiseaseViewHolder> {
    @NonNull
    @Override
    public DiseaseRecycler.DiseaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull DiseaseRecycler.DiseaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DiseaseViewHolder extends RecyclerView.ViewHolder{
        public DiseaseViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
