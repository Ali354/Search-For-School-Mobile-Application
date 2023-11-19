package com.example.search_for_school2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import models.Address;
import models.School;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.ViewHolder> {
    private List<School> schools;

    public SearchResultAdapter(List<School> schools) {
        this.schools = schools;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        School school = schools.get(position);
        holder.schoolName.setText(school.getName());
        holder.professorName.setText(school.getPhoneNumber());
//        String address_id = (string) school.getAddressId();
        holder.schoolAddress.setText("");
    }

    @Override
    public int getItemCount() {
        return schools.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView schoolName;
        public TextView professorName;
        public TextView schoolAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            schoolName = itemView.findViewById(R.id.school_name);
            professorName = itemView.findViewById(R.id.professor_name);
            schoolAddress = itemView.findViewById(R.id.school_address);
        }
    }
}
