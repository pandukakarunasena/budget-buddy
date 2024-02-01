package controllers;

import constants.TransactionType;
import model.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionManager {
    List<Transaction> getTransactionList();

    boolean isTransactionNotEmpty();

    Transaction getTransactionById(int transactionId);

    List<Transaction> getTransactionsByAmount(double amount);

    List<Transaction> getTransactionsByType(TransactionType transactionType);

    List<Transaction> getTransactionsByCategoryId(int categoryId);

    List<Transaction> getTransactionsByNote(String note);

    List<Transaction> getTransactionsByDate(Date date);

    boolean isTransactionExistingByTransactionId(int transactionId);

    boolean isTransactionsExistingByCategoryId(int categoryId);

    boolean isTransactionsExistingByType(TransactionType transactionType);

    double getTotalAmountForCategoryInCurrentMonth(int categoryId);

    void addTransaction(double amount, TransactionType transactionType, int categoryId, String note);

    void updateTransaction(int transactionId, double amount, TransactionType transactionType, int categoryId, String note);

    void removeTransactionByTransactionId(int transactionId);

    void removeTransactionsByCategoryId(int categoryId);

    String getPrintableTransactionList(String filter, int id);
}
