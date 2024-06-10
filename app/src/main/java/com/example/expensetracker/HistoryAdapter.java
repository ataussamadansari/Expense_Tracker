package com.example.expensetracker;

import android.content.Context;
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
        holder.cat_Name.setText(historyModelArrayList.get(position).getCat_Name());
        holder.type.setText(historyModelArrayList.get(position).getPayment_method());
        holder.amount.setText(historyModelArrayList.get(position).getAmount());
        holder.date.setText(historyModelArrayList.get(position).getDate());
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
