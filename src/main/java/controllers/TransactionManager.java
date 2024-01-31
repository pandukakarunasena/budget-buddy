/**
 * This class is used to manage Transactions.
 */
package controllers;

import constants.TransactionType;
import model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class TransactionManager {
    /** Transaction List. */
    private final List<Transaction> transactionList;

    /** Last Transaction ID. Used to calculate the new transaction ID when adding a new transaction. */
    private int lastTransactionId;

    /** TransactionManager instance. */
    private static TransactionManager instance;

    /**
     * Constructor for TransactionManager.
     */
    private TransactionManager() {
        this.transactionList = new ArrayList<>();
        this.lastTransactionId = 0;
    }

    /**
     * Get TransactionManager instance.
     *
     * @return TransactionManager instance
     */
    public static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    /**
     * Add Transaction.
     *
     * @param amount          Amount
     * @param transactionType Transaction Type
     * @param categoryId      Category ID
     * @param note            Note
     * @param date            Date
     */
    public void addTransaction(double amount, TransactionType transactionType, int categoryId, String note, Date date) {
        Transaction transaction = new Transaction(++lastTransactionId, amount, transactionType, categoryId, note, date);
        transactionList.add(transaction);
    }

    /**
     * Get Transaction from Transaction ID.
     *
     * @param transactionId Transaction ID
     * @return Transaction
     */
    public Transaction getTransaction(int transactionId) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionId() == transactionId) {
                return transaction;
            }
        }
        //throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND + transactionId);
        return null;
    }

    public boolean isTransactionExisting(int transactionId) {
        if (this.getTransaction(transactionId) != null) {
            return true;
        }
        return false;
    }

    /**
     * Get Transaction List.
     *
     * @return Transaction List
     */
    public List<Transaction> getTransactionList() {
        // TODO: Pagination
        return transactionList;
    }

    /**
     * Get Transaction List from Category ID.
     *
     * @param transactionId     Transaction ID
     * @param amount            Amount
     * @param transactionType   Transaction Type
     * @param categoryId        Category ID
     * @param note              Note
     * @param date              Date
     */
    public void editTransaction(int transactionId, double amount, TransactionType transactionType, int categoryId, String note, Date date) {
        Transaction transaction = getTransaction(transactionId);
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setCategoryId(categoryId);
        transaction.setNote(note);
        transaction.setDate(date);
    }

    /**
     * Remove Transaction.
     *
     * @param transactionId Transaction ID
     */
    public void removeTransaction(int transactionId) {
        transactionList.remove(getTransaction(transactionId));
    }

    public void removeAllTransactionsWithCategoryId(int categoryId) {
        transactionList.removeIf(transaction -> transaction.getCategoryId() == categoryId);
    }
}

