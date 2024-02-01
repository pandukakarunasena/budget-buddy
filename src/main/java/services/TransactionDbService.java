package services;

import constants.TransactionType;
import model.Transaction;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionDbService {

    List<Transaction> getAllTransactions();

    boolean isTransactionEmpty();

    Optional<Transaction> getTransactionById(int transactionId);

    List<Transaction> getTransactionsByAmount(double amount);

    List<Transaction> getTransactionsByType(TransactionType transactionType);

    List<Transaction> getTransactionsByCategoryId(int categoryId);

    List<Transaction> getTransactionsByNote(String note);

    List<Transaction> getTransactionsByDate(Date date);

    void addAllTransactions(List<Transaction> transactions);

    void addSingleTransaction(Transaction transaction);

    void updateTransaction(Transaction updatedTransaction);

    void removeTransactionByTransactionId(int transactionId);

    void removeTransactionsByCategoryId(int categoryId);

    void removeAllTransactions();
}
