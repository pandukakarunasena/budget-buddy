/**
 * Model class for Category.
 */
package model;

import constants.TransactionType;

public class Category {
    /** Category ID. */
    private final int categoryId;

    /** Category Name. */
    private String categoryName;

    /** Transaction Type. */
    private TransactionType transactionType;

    /**
     * Constructor for Category.
     *
     * @param categoryId     Category ID
     * @param categoryName   Category Name
     * @param transactionType Transaction Type
     */
    public Category(int categoryId, String categoryName, TransactionType transactionType) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.transactionType = transactionType;
    }

    /**
     * Get Category ID.
     *
     * @return Category ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Get Category Name.
     *
     * @return Category Name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Get Transaction Type.
     *
     * @return Transaction Type
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Set Category Name.
     *
     * @param categoryName Category Name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Set Transaction Type.
     *
     * @param transactionType Transaction Type
     */
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    /**
     * Override toString() method.
     *
     * @return String
     */
    @Override
    public String toString() {
        return categoryId + "\t\t" + categoryName + "\t\t" + transactionType;
    }
}

