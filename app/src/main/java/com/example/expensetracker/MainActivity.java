package com.example.expensetracker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensetracker.database.DatabaseHelper;
import com.example.expensetracker.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper dbHelper;
    ArrayList<HistoryModel> history = new ArrayList<>();
    HistoryAdapter adapter;
    ActivityMainBinding binding;

    //dialog
    TextView incomeBtn, expenseBtn;
    Button addBtn, cancelBtn;
    EditText cat_name, amount, date;
    Spinner method;
    String cat_name_str, amount_str, date_str, method_str;
    int amount_int;
    int t_amount;
    int income_amount, expense_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);
        // Get a writable database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        binding.addBtn.setOnClickListener(v -> {
            openDialog();
        });

        binding.historyRV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(this, history);
        binding.historyRV.setAdapter(adapter);

        Cursor cursor = dbHelper.getExpenses();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String cat_Name = cursor.getString(1);
                int amount = cursor.getInt(2);
                String date = cursor.getString(3);
                String methode = cursor.getString(4);
                HistoryModel task = new HistoryModel(cat_Name, amount, date, methode);
                history.add(task);

                t_amount = amount + t_amount;

                if (amount > 0) {
                    income_amount = Math.abs(amount) + income_amount;
                } else {
                    expense_amount = Math.abs(amount) + expense_amount;
                }
            }
        } else {
            Toast.makeText(this, "No tasks found", Toast.LENGTH_SHORT).show();
        }
        setAmount();

    }

    private void openDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.dialog_bg));
        dialog.setCancelable(false);
        dialog.show();

        incomeBtn = dialog.findViewById(R.id.incomeBtn);
        expenseBtn = dialog.findViewById(R.id.expenseBtn);
        addBtn = dialog.findViewById(R.id.addBtn);
        cancelBtn = dialog.findViewById(R.id.cancelBtn);
        cat_name = dialog.findViewById(R.id.cat_name);
        amount = dialog.findViewById(R.id.amount);
        date = dialog.findViewById(R.id.date);
        method = dialog.findViewById(R.id.pay_method);

        date.setOnClickListener(v -> showCalender());

        method.setSelection(0);
        String[] item = {"Select Method", "Cash", "Card", "UPI"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, item);
        method.setAdapter(adapter1);

        incomeBtn.setOnClickListener(v -> {
            incomeBtn.setBackground(getDrawable(R.drawable.inc_sel_btn_bg));
            expenseBtn.setBackground(getDrawable(R.drawable.btn_bg));

            addBtn.setOnClickListener(v1 -> {
                cat_name_str = cat_name.getText().toString();
                amount_str = amount.getText().toString();
                date_str = date.getText().toString();
                method_str = method.getSelectedItem().toString();

                if (cat_name_str.isEmpty()) {
                    cat_name.setError("Please enter category name");
                    return;
                }

                if (amount_str.isEmpty()) {
                    amount.setError("Please enter amount");
                    return;
                }

                if (date_str.isEmpty()) {
                    date.setError("Please enter date");
                    return;
                }

                if (method_str.equals("Select Method")) {
                    Toast.makeText(this, "Please select method", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (amount_str.equals("0")) {
                    amount.setError("Please enter amount");
                    return;
                }


                dialog.dismiss();
                amount_int = Integer.parseInt(amount_str);
                history.add(new HistoryModel(cat_name_str, amount_int, date_str, method_str));
                adapter.notifyDataSetChanged();
                dbHelper.insertExpense(cat_name_str, String.valueOf(amount_int), date_str, method_str);
                Toast.makeText(this, "Income Added", Toast.LENGTH_SHORT).show();
                setAmount();
            });

        });

        expenseBtn.setOnClickListener(v -> {
            expenseBtn.setBackground(getDrawable(R.drawable.exp_sel_btn_bg));
            incomeBtn.setBackground(getDrawable(R.drawable.btn_bg));

            // Add button click listener
            addBtn.setOnClickListener(v1 -> {
                cat_name_str = cat_name.getText().toString();
                amount_str = amount.getText().toString();
                date_str = date.getText().toString();
                method_str = method.getSelectedItem().toString();
                if (cat_name_str.isEmpty()) {
                    cat_name.setError("Please enter category name");
                    return;
                }
                if (amount_str.isEmpty()) {
                    amount.setError("Please enter amount");
                    return;
                }
                if (date_str.isEmpty()) {
                    date.setError("Please enter date");
                    return;
                }
                if (method_str.equals("Select Method")) {
                    Toast.makeText(this, "Please select method", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (amount_str.equals("0")) {
                    amount.setError("Please enter amount");
                    return;
                }
                dialog.dismiss();

                amount_int = Integer.parseInt(amount_str);
                amount_int = -amount_int;
                history.add(new HistoryModel(cat_name_str, amount_int, date_str, method_str));
                adapter.notifyDataSetChanged();
                dbHelper.insertExpense(cat_name_str, String.valueOf(amount_int), date_str, method_str);
                Toast.makeText(this, "Expense Added", Toast.LENGTH_SHORT).show();
                setAmount();
            });
        });

        cancelBtn.setOnClickListener(v -> dialog.dismiss());

    }

    private String showCalender() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view, year1, month1, dayOfMonth) -> {
            date.setText(dayOfMonth + "/" + (month1 + 1) + "/" + year1);
        }, year, month, day);
        datePickerDialog.show();
        return date.getText().toString();
    }

    private void setAmount() {
        binding.totalAmount.setText(String.valueOf(t_amount));
        binding.incomeAmount.setText(String.valueOf(income_amount));
        binding.expenseAmount.setText(String.valueOf(expense_amount));
    }
}