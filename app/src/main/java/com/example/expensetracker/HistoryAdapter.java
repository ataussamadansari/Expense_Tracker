package com.example.expensetracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.viewHolder> {
    Context context;
    ArrayList<HistoryModel> historyModelArrayList;

    public HistoryAdapter(Context context, ArrayList<HistoryModel> historyModelArrayList) {
        this.context = context;
        this.historyModelArrayList = historyModelArrayList;
    }
    @NonNull
    @Override
    public HistoryAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.viewHolder holder, int position) {
        HistoryModel model = historyModelArrayList.get(position);
        holder.cat_Name.setText(model.getCat_Name());
        holder.type.setText(model.getPayment_method());
        holder.amount.setText(model.getAmount());
        holder.date.setText(model.getDate());

        // Determine the amount color based on positive or negative value
        try {
            int amount = Integer.parseInt(model.getAmount());
            if (amount > 0) {
                holder.amount.setTextColor(Color.GREEN);
            } else {
                holder.amount.setTextColor(Color.RED);
            }
        } catch (NumberFormatException e) {
            // Handle the case where the amount is not a valid integer
            holder.amount.setTextColor(Color.BLACK); // Default color
        }

    }

    @Override
    public int getItemCount() {
        return historyModelArrayList.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView cat_Name, type, amount, date;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            cat_Name = itemView.findViewById(R.id.cat_Name);
            type = itemView.findViewById(R.id.payment_methode);
            amount = itemView.findViewById(R.id.amount);
            date = itemView.findViewById(R.id.date);
        }
    }
}
