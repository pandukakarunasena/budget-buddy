package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.TransactionType;
import model.Category;
import model.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TransactionDbService {
    private final String folderPath = "src/main/java/db/";
    private final String transactionFilePath = "transaction.json";
    private final ObjectMapper objectMapper;
    private volatile static TransactionDbService instance;

    public static TransactionDbService getInstance() {
        if (instance == null) {
            synchronized (CategoryDbService.class) {
                if (instance == null) {
                    instance = new TransactionDbService();
                }
            }
        }
        return instance;
    }

    private void initializeDataFile() {
        File file = new File(folderPath, transactionFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public TransactionDbService() {
        initializeDataFile();
        this.objectMapper = new ObjectMapper();
    }

    public List<Transaction> getAllTransactions() {
        try {
            File file = new File(folderPath, transactionFilePath);

            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Transaction>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean isTransactionEmpty() {
        List<Transaction> transactions = getAllTransactions();
        return transactions == null || transactions.isEmpty();
    }

    public Optional<Transaction> getTransactionById(int transactionId) {
        List<Transaction> transactions = getAllTransactions();
        return transactions.stream()
                .filter(category -> category.getTransactionId() == transactionId)
                .findFirst();
    }

    public List<Transaction> getTransactionsByAmount(double amount) {
        List<Transaction> allTransactions = getAllTransactions();
        return allTransactions.stream()
                .filter(transaction -> transaction.getAmount() == amount)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsByType(TransactionType transactionType) {
        List<Transaction> allTransactions = getAllTransactions();
        return allTransactions.stream()
                .filter(transaction -> transaction.getTransactionType() == transactionType)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsByCategoryId(int categoryId) {
        List<Transaction> allTransactions = getAllTransactions();
        return allTransactions.stream()
                .filter(transaction -> transaction.getCategoryId() == categoryId)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsByNote(String note) {
        List<Transaction> allTransactions = getAllTransactions();
        return allTransactions.stream()
                .filter(transaction -> note.equals(transaction.getNote()))
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsByDate(Date date) {
        List<Transaction> allTransactions = getAllTransactions();
        return allTransactions.stream()
                .filter(transaction -> transaction.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public synchronized void addAllTransactions(List<Transaction> transactions) {
        try {
            objectMapper.writeValue(new File(folderPath, transactionFilePath), transactions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addSingleTransaction(Transaction transaction) {
        List<Transaction> transactions = getAllTransactions();
        transactions.add(transaction);
        addAllTransactions(transactions);
    }

    public synchronized void updateTransaction(Transaction updatedTransaction) {
        List<Transaction> transactions = getAllTransactions();
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction.getTransactionId() == updatedTransaction.getTransactionId()) {
                transactions.set(i, updatedTransaction);
                addAllTransactions(transactions);
                return;
            }
        }
    }

    public synchronized void removeTransactionByTransactionId(int transactionId) {
        List<Transaction> transactions = getAllTransactions();
        transactions.removeIf(transaction -> transaction.getTransactionId() == transactionId);
        addAllTransactions(transactions);
    }

    public synchronized void removeTransactionsByCategoryId(int categoryId) {
        List<Transaction> transactions = getAllTransactions();
        transactions.removeIf(transaction -> transaction.getCategoryId() == categoryId);
        addAllTransactions(transactions);
    }

}
