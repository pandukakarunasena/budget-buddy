package model;

import constants.TransactionType;

import java.util.Date;

public class Transaction {
    private final int transactionId;
    private double amount;
    private TransactionType transactionType;
    private int categoryId;
    private String note;
    private Date date;

    public Transaction(int transactionId, double amount, TransactionType transactionType, int categoryId, String note, Date date) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.categoryId = categoryId;
        this.note = note;
        this.date = date;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return transactionId + "\t\t" + amount + "\t\t" + transactionType + "\t\t" + categoryId + "\t\t" + note + "\t\t" + date;
    }
}

