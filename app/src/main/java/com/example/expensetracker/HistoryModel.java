package com.example.expensetracker;

public class HistoryModel {
    String cat_Name;
    int amount;
    String date;
    String payment_method;

    public HistoryModel(String cat_Name, int amount, String date, String payment_method) {
        this.cat_Name = cat_Name;
        this.amount = amount;
        this.date = date;
        this.payment_method = payment_method;
    }

    public String getCat_Name() {
        return cat_Name;
    }

    public void setCat_Name(String cat_Name) {
        this.cat_Name = cat_Name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }
}
