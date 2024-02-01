package controllers;

import constants.TransactionType;
import model.Category;
import model.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.CategoryDbService;
import services.CategoryDbServiceImpl;
import services.TransactionDbService;
import services.TransactionDbServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TransactionManagerImplTest {

    private TransactionManagerImpl transactionManager;
    private TransactionDbServiceImpl transactionDbService;

    @BeforeEach
    void setUp() {

        transactionDbService = mock(TransactionDbServiceImpl.class);
        transactionManager = new TransactionManagerImpl(transactionDbService);
    }

    @AfterEach
    void tearDown() {

        transactionManager = null;
    }

    @Test
    @DisplayName("Check class type of the TransactionManagerImpl")
    void testCheckClassType() {

        assertEquals(TransactionManagerImpl.class.getName(), transactionManager.getClass().getName());
    }

    @Test
    @DisplayName("Check if new transaction manager is returning an empty transaction list")
    void testGetEmptyTransactionList() {

        assertThrows(IllegalArgumentException.class, () -> transactionManager.getTransactionList());
    }

    @Test
    @DisplayName("Check if transaction manager is returning a proper transaction list")
    void testGetNonEmptyTransactionList() {

        List<Transaction> expectedTransactions = new ArrayList<>();

        // Add sample transactions to the list
        expectedTransactions.add(new Transaction(1, 100.0, TransactionType.Expense, 0, "Test Note", new Date()));
        expectedTransactions.add(new Transaction(2, 200.0, TransactionType.Expense, 0, "Test Note", new Date()));
        expectedTransactions.add(new Transaction(3, 300.0, TransactionType.Expense, 0, "Test Note", new Date()));
        expectedTransactions.add(new Transaction(4, 400.0, TransactionType.Expense, 0, "Test Note", new Date()));

        when(transactionDbService.getAllTransactions()).thenReturn(expectedTransactions);

        List<Transaction> actualTransactions = transactionManager.getTransactionList();
        assertEquals(4, actualTransactions.size());
        assertEquals(expectedTransactions, actualTransactions);
    }

//    @Test
//    void testIsTransactionNotEmpty() {
//        // Add test case to check if transaction is not empty
//    }
//
//    @Test
//    void testGetTransactionById() {
//        int transactionId = 1;
//        Transaction expectedTransaction = new Transaction(transactionId, 100.0, TransactionType.Expense, 1, "Test Note", new Date());
//
//        Transaction actualTransaction = transactionManager.getTransactionById(transactionId);
//
//        assertEquals(expectedTransaction, actualTransaction);
//    }
//
//    @Test
//    void testGetTransactionsByAmount() {
//        double amount = 100.0;
//        List<Transaction> expectedTransactions = new ArrayList<>();
//        // Add expected transactions to the list
//
//        List<Transaction> actualTransactions = transactionManager.getTransactionsByAmount(amount);
//
//        assertEquals(expectedTransactions, actualTransactions);
//    }
//
//    @Test
//    void testGetTransactionsByType() {
//        TransactionType transactionType = TransactionType.Expense;
//        List<Transaction> expectedTransactions = new ArrayList<>();
//        // Add expected transactions to the list
//
//        List<Transaction> actualTransactions = transactionManager.getTransactionsByType(transactionType);
//
//        assertEquals(expectedTransactions, actualTransactions);
//    }
//
//    @Test
//    void testGetTransactionsByCategoryId() {
//        int categoryId = 1;
//        List<Transaction> expectedTransactions = new ArrayList<>();
//        // Add expected transactions to the list
//
//        List<Transaction> actualTransactions = transactionManager.getTransactionsByCategoryId(categoryId);
//
//        assertEquals(expectedTransactions, actualTransactions);
//    }
//
//    @Test
//    void testGetTransactionsByNote() {
//        String note = "Test Note";
//        List<Transaction> expectedTransactions = new ArrayList<>();
//        // Add expected transactions to the list
//
//        List<Transaction> actualTransactions = transactionManager.getTransactionsByNote(note);
//
//        assertEquals(expectedTransactions, actualTransactions);
//    }
//
//    @Test
//    void testGetTransactionsByDate() {
//        Date date = new Date();
//        List<Transaction> expectedTransactions = new ArrayList<>();
//        // Add expected transactions to the list
//
//        List<Transaction> actualTransactions = transactionManager.getTransactionsByDate(date);
//
//        assertEquals(expectedTransactions, actualTransactions);
//    }
//
//    @Test
//    void testIsTransactionExistingByTransactionId() {
//        int transactionId = 1;
//
//        boolean isExisting = transactionManager.isTransactionExistingByTransactionId(transactionId);
//
//        assertTrue(isExisting);
//    }
//
//    @Test
//    void testIsTransactionsExistingByCategoryId() {
//        int categoryId = 1;
//
//        boolean isExisting = transactionManager.isTransactionsExistingByCategoryId(categoryId);
//
//        assertTrue(isExisting);
//    }
//
//    @Test
//    void testIsTransactionsExistingByType() {
//        TransactionType transactionType = TransactionType.Expense;
//
//        boolean isExisting = transactionManager.isTransactionsExistingByType(transactionType);
//
//        assertTrue(isExisting);
//    }
//
//    @Test
//    void testAddTransaction() {
//        double amount = 100.0;
//        TransactionType transactionType = TransactionType.Expense;
//        int categoryId = 1;
//        String note = "Test Note";
//
//        assertDoesNotThrow(() -> transactionManager.addTransaction(amount, transactionType, categoryId, note));
//    }
//
//    @Test
//    void testUpdateTransaction() {
//        int transactionId = 1;
//        double amount = 100.0;
//        TransactionType transactionType = TransactionType.Expense;
//        int categoryId = 1;
//        String note = "Test Note";
//
//        assertDoesNotThrow(() -> transactionManager.updateTransaction(transactionId, amount, transactionType, categoryId, note));
//    }
//
//    @Test
//    void testRemoveTransactionByTransactionId() {
//        int transactionId = 1;
//
//        assertDoesNotThrow(() -> transactionManager.removeTransactionByTransactionId(transactionId));
//    }
//
//    @Test
//    void testRemoveTransactionsByCategoryId() {
//        int categoryId = 1;
//
//        assertDoesNotThrow(() -> transactionManager.removeTransactionsByCategoryId(categoryId));
//    }
//
//    @Test
//    void testGetPrintableTransactionList() {
//        String filter = "all";
//        int id = 0;
//        String expectedPrintableList = "";
//        // Add expected printable transaction list
//
//        String actualPrintableList = transactionManager.getPrintableTransactionList(filter, id);
//
//        assertEquals(expectedPrintableList, actualPrintableList);
//    }
}