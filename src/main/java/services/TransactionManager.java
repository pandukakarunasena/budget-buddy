package services;

import constants.TransactionType;
import model.Transaction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionManager {
    private final List<Transaction> transactionList;
    private int lastTransactionId;

    private static TransactionManager instance;

    private TransactionManager() {
        this.transactionList = new ArrayList<>();
        this.lastTransactionId = 0;
    }

    public static TransactionManager getInstance() {
        if (instance == null) {
            instance = new TransactionManager();
        }
        return instance;
    }

    // Add Transaction
    public void addTransaction(double amount, TransactionType transactionType, int categoryId, String note, Date date) {
        Transaction transaction = new Transaction(lastTransactionId++, amount, transactionType, categoryId, note, date);
        transactionList.add(transaction);
    }

    // Get Transaction from Transaction ID
    public Transaction getTransaction(int transactionId) {
        for (Transaction transaction : transactionList) {
            if (transaction.getTransactionId() == transactionId) {
                return transaction;
            }
        }
        throw new IllegalArgumentException("Transaction not found for transaction ID: " + transactionId);
    }

    // Get Transaction List
    public List<Transaction> getTransactionList() {
        // TODO: Pagination
        return transactionList;
    }

    // Edit Transaction
    public void editTransaction(int transactionId, double amount, TransactionType transactionType, int categoryId, String note, Date date) {
        Transaction transaction = getTransaction(transactionId);
        transaction.setAmount(amount);
        transaction.setTransactionType(transactionType);
        transaction.setCategoryId(categoryId);
        transaction.setNote(note);
        transaction.setDate(date);
    }

    // Remove Transaction
    public void removeTransaction(int transactionId) {
        transactionList.remove(getTransaction(transactionId));
    }
}

