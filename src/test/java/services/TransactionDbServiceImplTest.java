package services;

import constants.TransactionType;
import model.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionDbServiceImplTest {

    @BeforeEach
    void setup() {

        TransactionDbServiceImpl.getInstance().removeAllTransactions();
    }

    @AfterEach
    void cleanCategoryList() {

        TransactionDbServiceImpl.getInstance().removeAllTransactions();
    }

    @Test
    @DisplayName("Check class type of the TransactionDbServiceImpl")
    void getInstanceCheckClassType() {

        TransactionDbService transactionDbService = TransactionDbServiceImpl.getInstance();
        assertEquals(TransactionDbServiceImpl.class.getName(), transactionDbService.getClass().getName());
    }

    @Test
    @DisplayName("Check if a single object of TransactionDBService is present")
    void getInstanceCheckSingleton() {

        TransactionDbService transactionDbService1 = TransactionDbServiceImpl.getInstance();
        TransactionDbService transactionDbService2 = TransactionDbServiceImpl.getInstance();
        assertEquals(transactionDbService1, transactionDbService2);
    }

    @Test
    @DisplayName("Get all transaction when no transactions added")
    void getAllCategoriesWhenNoTransactions() {

        TransactionDbService transactionDbService = TransactionDbServiceImpl.getInstance();
        List<Transaction> transactionList = transactionDbService.getAllTransactions();
        assertEquals(0, transactionList.size());
    }

    @Test
    @DisplayName("Get all transactions when two transactions added")
    void getAllCategoriesWhenTwoTransactions() {

        TransactionDbService transactionDbService = TransactionDbServiceImpl.getInstance();
        transactionDbService.addSingleTransaction(
                new Transaction(1, 300, TransactionType.Income, 1, "test", new Date()));

        transactionDbService.addSingleTransaction(
                new Transaction(2, 400, TransactionType.Income, 1, "test", new Date()));
        List<Transaction> transactionList = transactionDbService.getAllTransactions();
        assertEquals(2, transactionList.size());
    }

    @Test
    @DisplayName("Check empty transaction list")
    void isCategoryEmpty() {

        assertTrue(TransactionDbServiceImpl.getInstance().isTransactionEmpty());
    }

    @Test
    @DisplayName("Get a transaction by transaction ID")
    void getTransactionById() {

        Transaction transactionToBeAdded = new Transaction(
                1,300,TransactionType.Income,1,"test",new Date());
        TransactionDbServiceImpl.getInstance().addSingleTransaction(transactionToBeAdded);

        Transaction transactionRetrieved = TransactionDbServiceImpl.getInstance().getTransactionById(1).get();
        assertEquals(transactionToBeAdded.getTransactionId(), transactionRetrieved.getTransactionId());
    }

    @Test
    void getTransactionsByType() {

        Transaction transactionToBeAdded = new Transaction(
                1,300,TransactionType.Income,1,"test",new Date());
        TransactionDbServiceImpl.getInstance().addSingleTransaction(transactionToBeAdded);

        List<Transaction> transactionsRetrieved = TransactionDbServiceImpl.getInstance()
                .getTransactionsByType(TransactionType.Income);

        assertEquals(1,transactionsRetrieved.size());
    }

    @Test
    void getTransactionsByCategoryId() {

        Transaction transactionToBeAdded = new Transaction(
                1,300,TransactionType.Income,1,"test",new Date());
        TransactionDbServiceImpl.getInstance().addSingleTransaction(transactionToBeAdded);

        List<Transaction> transactionsRetrieved = TransactionDbServiceImpl.getInstance()
                .getTransactionsByCategoryId(1);

        assertEquals(1,transactionsRetrieved.size());
    }

    @Test
    void getTransactionsByDate() {

        Date date = new Date();
        Transaction transactionToBeAdded = new Transaction(
                1,300,TransactionType.Income,1,"test",date);
        TransactionDbServiceImpl.getInstance().addSingleTransaction(transactionToBeAdded);

        List<Transaction> transactionsRetrieved = TransactionDbServiceImpl.getInstance()
                .getTransactionsByDate(date);

        assertEquals(1,transactionsRetrieved.size());
    }

    @Test
    void addAllTransactions() {

        List<Transaction> transactionToBeAddedList = new ArrayList<>();
        transactionToBeAddedList.add(new Transaction(
                1,300,TransactionType.Income,1,"test", new Date())
        );
        transactionToBeAddedList.add(new Transaction(
                2,400,TransactionType.Income,1,"test", new Date())
        );

        TransactionDbServiceImpl.getInstance().addAllTransactions(transactionToBeAddedList);
        List<Transaction> transactionListRetrieved = TransactionDbServiceImpl.getInstance().getAllTransactions();
        assertEquals(transactionToBeAddedList.size(), transactionListRetrieved.size());
    }

    @Test
    void addSingleTransaction() {

        Transaction transactionToBeAdded = new Transaction(
                2,400,TransactionType.Income,1,"test", new Date());
        TransactionDbServiceImpl.getInstance().addSingleTransaction(transactionToBeAdded);

        Transaction transactionRetrieved = TransactionDbServiceImpl.getInstance().getTransactionById(2).get();

        assertEquals(transactionRetrieved.getTransactionId(), transactionToBeAdded.getTransactionId());
    }

    @Test
    void updateTransaction() {

        Transaction transactionToBeAdded = new Transaction(
                2,400,TransactionType.Income,1,"test", new Date());
        TransactionDbServiceImpl.getInstance().addSingleTransaction(transactionToBeAdded);

        Transaction transactionToBeUpdated = new Transaction(
                2,500,TransactionType.Income,1,"test", new Date()
        );

        TransactionDbServiceImpl.getInstance().updateTransaction(transactionToBeUpdated);

        Transaction transactionRetrieved = TransactionDbServiceImpl.getInstance().getTransactionById(2).get();

        assertEquals(transactionToBeUpdated.getTransactionId(), transactionRetrieved.getTransactionId());
    }

    @Test
    void removeTransactionByTransactionId() {

        Transaction transactionToBeAdded = new Transaction(
                2,400,TransactionType.Income,1,"test", new Date());
        TransactionDbServiceImpl.getInstance().addSingleTransaction(transactionToBeAdded);

        TransactionDbServiceImpl.getInstance().removeTransactionByTransactionId(2);
        assertTrue(TransactionDbServiceImpl.getInstance().getAllTransactions().isEmpty());
    }

    @Test
    void removeTransactionsByCategoryId() {

        Transaction transactionToBeAdded = new Transaction(
                2,400,TransactionType.Income,1,"test", new Date());
        TransactionDbServiceImpl.getInstance().addSingleTransaction(transactionToBeAdded);

        TransactionDbServiceImpl.getInstance().removeTransactionsByCategoryId(1);
        assertTrue(TransactionDbServiceImpl.getInstance().getAllTransactions().isEmpty());
    }
}