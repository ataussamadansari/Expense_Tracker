package com.example.expensetracker;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<HistoryModel> history = new ArrayList<>();
    HistoryAdapter adapter;

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.historyRV);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        history.add(new HistoryModel("1", "Grocery", "-300", "05/07/2024", "Card"));
        history.add(new HistoryModel("2", "Shopping", "-1500", "6/07/2024", "Paytm"));
        history.add(new HistoryModel("3", "Rent", "-3000", "08/07/2024", "Cash"));
        history.add(new HistoryModel("4", "Salary", "15000", "15/07/2024", "Cash"));


        adapter = new HistoryAdapter(this, history);
        recyclerView.setAdapter(adapter);
    }
}