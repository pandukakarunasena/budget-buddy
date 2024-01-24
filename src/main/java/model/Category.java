package model;

import constants.TransactionType;

public class Category {
    private final int categoryId;
    private String categoryName;
    private TransactionType transactionType;

    public Category(int categoryId, String categoryName, TransactionType transactionType) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.transactionType = transactionType;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    @Override
    public String toString() {
        return categoryId + "\t\t" + categoryName;
    }
}

