package com.example.expensetracker.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    // Database Name
    private static final String DATABASE_NAME = "expenses.db";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Table Name
    public static final String TABLE_EXPENSES = "expenses";

    // Table Columns
    public static final String COLUMN_CAT_ID = "cat_id";
    public static final String COLUMN_CAT_NAME = "cat_name";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_PAYMENT_METHOD = "payment_method";
    // SQL query to create the table
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_EXPENSES + " (" +
                    COLUMN_CAT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_CAT_NAME + " TEXT NOT NULL, " +
                    COLUMN_AMOUNT + " TEXT NOT NULL, " +
                    COLUMN_DATE + " TEXT NOT NULL, " +
                    COLUMN_PAYMENT_METHOD + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSES);
        onCreate(db);
    }

    public void insertExpense(String catName, String amount, String date, String paymentMethod) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + TABLE_EXPENSES + " (" + COLUMN_CAT_NAME + ", " + COLUMN_AMOUNT + ", " + COLUMN_DATE + ", " + COLUMN_PAYMENT_METHOD + ") VALUES ('"
                + catName + "', '" + amount + "', '" + date + "', '" + paymentMethod + "')");
        db.close();
    }

    public Cursor getExpenses() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXPENSES, null);
        return cursor;
    }

    public void deleteExpense(int catId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_EXPENSES + " WHERE " + COLUMN_CAT_ID + " = " + catId);
        db.close();
    }

    public void updateExpense(int catId, String catName, String amount, String date, String paymentMethod) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_EXPENSES + " SET " + COLUMN_CAT_NAME + " = '" + catName + "', " + COLUMN_AMOUNT + " = '" + amount + "', " + COLUMN_DATE + " = '" + date + "', " + COLUMN_PAYMENT_METHOD + " = '" + paymentMethod + "' WHERE " + COLUMN_CAT_ID + " = " + catId);
        db.close();
    }

    //get Only Amount
    public Cursor getAmount() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_AMOUNT + " FROM " + TABLE_EXPENSES, null);
        return cursor;
    }

}
