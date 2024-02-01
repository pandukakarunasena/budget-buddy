/**
 * Model class for Transaction.
 */
package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import constants.TransactionType;

import java.util.Date;

public class Transaction {
    /** Transaction ID. */
    private final int transactionId;

    /** Amount. */
    private double amount;

    /** Transaction Type. */
    private TransactionType transactionType;

    /** Category ID. */
    private int categoryId;

    /** Note. */
    private String note;

    /** Date. */
    private Date date;

    /**
     * Constructor for Transaction.
     *
     * @param transactionId   Transaction ID
     * @param amount          Amount
     * @param transactionType Transaction Type
     * @param categoryId      Category ID
     * @param note            Note
     * @param date            Date
     */
    @JsonCreator
    public Transaction(
            @JsonProperty("transactionId") int transactionId,
            @JsonProperty("amount") double amount,
            @JsonProperty("transactionType") TransactionType transactionType,
            @JsonProperty("categoryId") int categoryId,
            @JsonProperty("note") String note,
            @JsonProperty("date") Date date) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.transactionType = transactionType;
        this.categoryId = categoryId;
        this.note = note;
        this.date = date;
    }

    /**
     * Get Transaction ID.
     *
     * @return Transaction ID
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * Get Amount.
     *
     * @return Amount
     */
    public double getAmount() {
        return amount;
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
     * Get Category ID.
     *
     * @return Category ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Get Note.
     *
     * @return Note
     */
    public String getNote() {
        return note;
    }

    /**
     * Get Date.
     *
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Set Amount.
     *
     * @param amount Amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
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
     * Set Category ID.
     *
     * @param categoryId Category ID
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Set Note.
     *
     * @param note Note
     */
    public void setNote(String note) {
        this.note = note;
    }

    /**
     * Set Date.
     *
     * @param date Date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Override toString() method.
     *
     * @return String
     */
    @Override
    public String toString() {
        return transactionId + "\t\t" + amount + "\t\t" + transactionType + "\t\t" + categoryId + "\t\t" + note + "\t\t" + date;
    }
}

