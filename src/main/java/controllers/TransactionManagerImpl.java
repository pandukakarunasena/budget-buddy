/**
 * This class is used to manage Transactions.
 */
package controllers;

import constants.Constants;
import constants.Month;
import constants.TransactionType;
import model.Transaction;
import services.TransactionDbService;
import services.TransactionDbServiceImpl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionManagerImpl implements TransactionManager {
    private final TransactionDbService transactionDbService;
    private final AtomicInteger lastTransactionId;

    public TransactionManagerImpl() {
        this.transactionDbService = TransactionDbServiceImpl.getInstance();
        this.lastTransactionId = new AtomicInteger(getMaxTransactionId());
    }

    public TransactionManagerImpl(TransactionDbServiceImpl transactionDbService) {
        this.transactionDbService = transactionDbService;
        this.lastTransactionId = new AtomicInteger(getMaxTransactionId());
    }

    private int getMaxTransactionId() {
        List<Transaction> transactionList = transactionDbService.getAllTransactions();
        return transactionList.stream()
                .mapToInt(Transaction::getTransactionId)
                .max()
                .orElse(0);
    }

    @Override
    public List<Transaction> getTransactionList() {
        List<Transaction> transactionList = transactionDbService.getAllTransactions();
        if (transactionList != null && !transactionList.isEmpty()) {
            return transactionList;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_TRANSACTION);
        }
    }

    @Override
    public boolean isTransactionNotEmpty() {
        if (!transactionDbService.isTransactionEmpty()) {
            return true;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_TRANSACTION);
        }
    }

    @Override
    public Transaction getTransactionById(int transactionId) {
        Optional<Transaction> optionalTransaction = transactionDbService.getTransactionById(transactionId);
        if (optionalTransaction.isPresent()) {
            return optionalTransaction.get();
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_ID + transactionId);
        }
    }

    @Override
    public List<Transaction> getTransactionsByAmount(double amount) {
        List<Transaction> transactions = transactionDbService.getTransactionsByAmount(amount);
        if (transactions != null && !transactions.isEmpty()) {
            return transactions;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_AMOUNT + amount);
        }
    }

    @Override
    public List<Transaction> getTransactionsByType(TransactionType transactionType) {
        List<Transaction> transactions = transactionDbService.getTransactionsByType(transactionType);
        if (transactions != null && !transactions.isEmpty()) {
            return transactions;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_TYPE + transactionType);
        }
    }

    @Override
    public List<Transaction> getTransactionsByCategoryId(int categoryId) {
        List<Transaction> transactions = transactionDbService.getTransactionsByCategoryId(categoryId);
        if (transactions != null && !transactions.isEmpty()) {
            return transactions;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_CATEGORY_ID + categoryId);
        }
    }

    @Override
    public List<Transaction> getTransactionsByNote(String note) {
        List<Transaction> transactions = transactionDbService.getTransactionsByNote(note);
        if (transactions != null && !transactions.isEmpty()) {
            return transactions;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_NOTE + note);
        }
    }

    @Override
    public List<Transaction> getTransactionsByDate(Date date) {
        List<Transaction> transactions = transactionDbService.getTransactionsByDate(date);
        if (transactions != null && !transactions.isEmpty()) {
            return transactions;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_DATE + date);
        }
    }

    @Override
    public boolean isTransactionExistingByTransactionId(int transactionId) {
        try {
            Transaction returnedTransaction = getTransactionById(transactionId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean isTransactionsExistingByCategoryId(int categoryId) {
        try {
            List<Transaction> returnedTransactions = getTransactionsByCategoryId(categoryId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public boolean isTransactionsExistingByType(TransactionType transactionType) {
        try {
            List<Transaction> returnedTransactions = getTransactionsByType(transactionType);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private int getTransactionMonth(Transaction transaction) {
        LocalDate localDate = transaction.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }

    public double getTotalAmountForCategoryInCurrentMonth(int categoryId) {
        List<Transaction> transactionList = getTransactionsByCategoryId(categoryId);

        int currentMonth = LocalDate.now().getMonthValue();

        double totalAmount = transactionList.stream()
                .filter(transaction -> getTransactionMonth(transaction) == currentMonth
                && transaction.getTransactionType() == TransactionType.Expense)
                .mapToDouble(Transaction::getAmount)
                .sum();

        return totalAmount;
    }

    @Override
    public void addTransaction(double amount, TransactionType transactionType, int categoryId, String note) {
        CategoryManager categoryManager = new CategoryManagerImpl();
        BudgetManager budgetManager = new BudgetManagerImpl();

        if (categoryManager.isCategoryExistingById(categoryId)) {
            int currentMonth = LocalDate.now().getMonthValue();
            Month[] months = Month.values();
            Month currentMonthEnum = months[currentMonth - 1];

            double currentTotal = getTotalAmountForCategoryInCurrentMonth(categoryId);
            double total;
            if (transactionType == TransactionType.Expense) {
                total = currentTotal + amount;
            } else {
                total = currentTotal;
            }
            boolean exceedBudget = budgetManager.doesExpensesExceedBudget(categoryId, currentMonthEnum, total);
            if (!exceedBudget) {
                int newTransactionId = lastTransactionId.incrementAndGet();
                Transaction transaction = new Transaction(newTransactionId, amount, transactionType, categoryId, note, new Date());
                transactionDbService.addSingleTransaction(transaction);
            } else {
                throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTIONS_EXCEED_BUDGET);
            }
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_ID + categoryId);
        }
    }

    @Override
    public void updateTransaction(int transactionId, double amount, TransactionType transactionType, int categoryId, String note) {
        CategoryManager categoryManager = new CategoryManagerImpl();

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

    @Override
    public void removeTransactionByTransactionId(int transactionId) {
        if (isTransactionExistingByTransactionId(transactionId)) {
            transactionDbService.removeTransactionByTransactionId(transactionId);
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_ID + transactionId);
        }
    }

    @Override
    public void removeTransactionsByCategoryId(int categoryId) {
        if (isTransactionsExistingByCategoryId(categoryId)) {
            transactionDbService.removeTransactionsByCategoryId(categoryId);
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_CATEGORY_ID + categoryId);
        }
    }

    @Override
    public String getPrintableTransactionList(String filter, int id) {
        List<Transaction> transactionsList = null;
        if (filter.equals("all")) {
            transactionsList = transactionDbService.getAllTransactions();
        } else if (filter.equals("filter-category") && id > 0) {
            transactionsList = transactionDbService.getTransactionsByCategoryId(id);
        } else if (filter.equals("filter-type")) {
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

