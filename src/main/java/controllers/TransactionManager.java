/**
 * This class is used to manage Transactions.
 */
package controllers;

import constants.Constants;
import constants.TransactionType;
import model.Category;
import model.Transaction;
import services.TransactionDbService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManager {
    private final TransactionDbService transactionDbService;
    private final AtomicInteger lastTransactionId;

    public TransactionManager() {
        this.transactionDbService = TransactionDbService.getInstance();
        this.lastTransactionId = new AtomicInteger(getMaxTransactionId());
    }

    private int getMaxTransactionId() {
        List<Transaction> transactionList = transactionDbService.getAllTransactions();
        return transactionList.stream()
                .mapToInt(Transaction::getTransactionId)
                .max()
                .orElse(0);
    }

    public List<Transaction> getTransactionList() {
        List<Transaction> transactionList = transactionDbService.getAllTransactions();
        if (transactionList != null && !transactionList.isEmpty()) {
            return transactionList;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_TRANSACTION);
        }
    }

    public boolean isTransactionNotEmpty() {
        if (!transactionDbService.isTransactionEmpty()) {
            return true;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_TRANSACTION);
        }
    }

    public Transaction getTransactionById(int transactionId) {
        Optional<Transaction> optionalTransaction = transactionDbService.getTransactionById(transactionId);
        if (optionalTransaction.isPresent()) {
            return optionalTransaction.get();
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_ID + transactionId);
        }
    }

    public List<Transaction> getTransactionsByAmount(double amount) {
        List<Transaction> transactions = transactionDbService.getTransactionsByAmount(amount);
        if (transactions != null && !transactions.isEmpty()) {
            return transactions;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_AMOUNT + amount);
        }
    }

    public List<Transaction> getTransactionsByType(TransactionType transactionType) {
        List<Transaction> transactions = transactionDbService.getTransactionsByType(transactionType);
        if (transactions != null && !transactions.isEmpty()) {
            return transactions;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_TYPE + transactionType);
        }
    }

    public List<Transaction> getTransactionsByCategoryId(int categoryId) {
        List<Transaction> transactions = transactionDbService.getTransactionsByCategoryId(categoryId);
        if (transactions != null && !transactions.isEmpty()) {
            return transactions;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_CATEGORY_ID + categoryId);
        }
    }

    public List<Transaction> getTransactionsByNote(String note) {
        List<Transaction> transactions = transactionDbService.getTransactionsByNote(note);
        if (transactions != null && !transactions.isEmpty()) {
            return transactions;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_NOTE + note);
        }
    }

    public List<Transaction> getTransactionsByDate(Date date) {
        List<Transaction> transactions = transactionDbService.getTransactionsByDate(date);
        if (transactions != null && !transactions.isEmpty()) {
            return transactions;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_DATE + date);
        }
    }

    public boolean isTransactionExistingByTransactionId(int transactionId) {
        try {
            Transaction returnedTransaction = getTransactionById(transactionId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isTransactionsExistingByCategoryId(int categoryId) {
        try {
            List<Transaction> returnedTransactions = getTransactionsByCategoryId(categoryId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean isTransactionsExistingByType(TransactionType transactionType) {
        try {
            List<Transaction> returnedTransactions = getTransactionsByType(transactionType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public void addTransaction(double amount, TransactionType transactionType, int categoryId, String note) {

        CategoryManager categoryManager = new CategoryManager();
        if (categoryManager.isCategoryExistingById(categoryId)) {
            int newTransactionId = lastTransactionId.incrementAndGet();
            Transaction transaction = new Transaction(newTransactionId, amount, transactionType, categoryId, note, new Date());
            transactionDbService.addSingleTransaction(transaction);
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_ID + categoryId);
        }
    }

    public void updateTransaction(int transactionId, double amount, TransactionType transactionType, int categoryId, String note) {
        CategoryManager categoryManager = new CategoryManager();

        if (categoryManager.isCategoryExistingById(categoryId)) {
            if (this.isTransactionNotEmpty()) {
                Transaction transaction = getTransactionById(transactionId);
                if (transaction != null) {
                    transaction.setAmount(amount);
                    transaction.setTransactionType(transactionType);
                    transaction.setCategoryId(categoryId);
                    transaction.setNote(note);
                    transaction.setDate(transaction.getDate());
                    transactionDbService.updateTransaction(transaction);
                } else {
                    throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_ID + categoryId);
                }
            } else {
                throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_TRANSACTION);
            }
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_ID + categoryId);
        }
    }

    public void removeTransactionByTransactionId(int transactionId) {
        if (isTransactionExistingByTransactionId(transactionId)) {
            transactionDbService.removeTransactionByTransactionId(transactionId);
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_ID + transactionId);
        }
    }

    public void removeTransactionsByCategoryId(int categoryId) {
        if (isTransactionsExistingByCategoryId(categoryId)) {
            transactionDbService.removeTransactionsByCategoryId(categoryId);
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_CATEGORY_ID + categoryId);
        }
    }

    public String getPrintableTransactionList(String what, int id) {
        List<Transaction> transactionsList = null;
        if (what.equals("all")) {
            transactionsList = transactionDbService.getAllTransactions();
        } else if (what.equals("filter-category") && id > 0) {
            transactionsList = transactionDbService.getTransactionsByCategoryId(id);
        } else if (what.equals("filter-type")) {
            if (id == 1) {
                transactionsList = transactionDbService.getTransactionsByType(TransactionType.Income);
            } else {
                transactionsList = transactionDbService.getTransactionsByType(TransactionType.Expense);
            }
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_PRINTABLE_TRANSACTION_LIST_DOES_NOT_EXIST);
        }

        if (transactionsList != null && !transactionsList.isEmpty()) {
            StringBuilder result = new StringBuilder();
            result.append("Transaction ID\tAmount\tTransaction Type\tCategory ID\tNote\tDate\n");

            for (Transaction transaction : transactionsList) {
                result.append(transaction.getTransactionId())
                        .append("\t\t")
                        .append(transaction.getAmount())
                        .append("\t\t")
                        .append(transaction.getTransactionType())
                        .append("\t\t")
                        .append(transaction.getCategoryId())
                        .append("\t\t")
                        .append(transaction.getNote())
                        .append("\t\t")
                        .append(transaction.getDate())
                        .append("\n");
            }

            return result.toString();
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_TRANSACTION);
        }
    }
}

