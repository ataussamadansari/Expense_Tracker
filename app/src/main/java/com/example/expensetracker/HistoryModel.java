package com.example.expensetracker;

public class HistoryModel {
    String cat_id;
    String cat_Name;
    String amount;
    String date;
    String payment_method;

    public HistoryModel(String cat_id, String cat_Name, String amount, String date, String payment_method) {
        this.cat_id = cat_id;
        this.cat_Name = cat_Name;
        this.amount = amount;
        this.date = date;
        this.payment_method = payment_method;
    }

    public String getCat_id() {
        return cat_id;
    }

    public String getCat_Name() {
        return cat_Name;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public void setCat_Name(String cat_Name) {
        this.cat_Name = cat_Name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

}
