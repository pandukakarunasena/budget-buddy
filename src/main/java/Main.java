import constants.TransactionType;
import model.Budget;
import model.Category;
import model.Transaction;
import services.BudgetManager;
import services.CategoryManager;
import services.TransactionManager;
import util.Util;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Main loop to show menu
        while (true) {
            // Get user input to select Transactions, Categories, Budgets, or Exit
            System.out.println("Main Menu");
            System.out.println("\t1. Transactions");
            System.out.println("\t2. Categories");
            System.out.println("\t3. Budgets");
            System.out.println("\t4. Exit");

            // Get user input
            System.out.print("Select an option:\t");
            int option = scanner.nextInt();

            // Switch case to handle user input
            switch (option) {
                case 1:
                    transactionMenu();
                    break;
                case 2:
                    categoryMenu();
                    break;
                case 3:
                    budgetMenu();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void transactionMenu() {
        TransactionManager transactionManager = TransactionManager.getInstance();

        int transactionId;
        double amount;
        int categoryId;
        String note;
        Date date;

        // Transactions Menu Loop
        while(true){
            // Transactions
            System.out.println("Transactions Menu");
            System.out.println("\t1. Add Income");
            System.out.println("\t2. Add Expense");
            System.out.println("\t3. Edit Transaction");
            System.out.println("\t4. Remove Transaction");
            System.out.println("\t5. View Transactions");
            System.out.println("\t6. Back");
            // Get user input
            System.out.print("Select an option:\t");
            int transactionOption = scanner.nextInt();
            // Switch case to handle user input
            switch (transactionOption) {
                case 1:
                    // Add Transaction
                    System.out.println("Add Transaction");
                    System.out.print("Enter amount:");
                    amount = scanner.nextDouble();
                    System.out.print("Enter category ID:");
                    categoryId = scanner.nextInt();
                    System.out.print("Enter note:");
                    note = scanner.next();
                    date = new Date();
                    transactionManager.addTransaction(amount, TransactionType.Income, categoryId, note, date);
                    break;
                case 2:
                    // Add Transaction
                    System.out.println("Add Transaction");
                    System.out.print("Enter amount:");
                    amount = scanner.nextDouble();
                    System.out.print("Enter category ID:");
                    categoryId = scanner.nextInt();
                    System.out.print("Enter note:");
                    note = scanner.next();
                    date = new Date();
                    transactionManager.addTransaction(amount, TransactionType.Expense, categoryId, note, date);
                    break;
                case 3:
                    // Edit Transaction
                    System.out.println("Edit Transaction");
                    System.out.print("Enter transaction ID:");
                    transactionId = scanner.nextInt();
                    System.out.print("Enter amount:");
                    amount = scanner.nextDouble();
                    System.out.print("Enter category ID:");
                    categoryId = scanner.nextInt();
                    System.out.print("Enter note:");
                    note = scanner.next();
                    date = new Date();
                    transactionManager.editTransaction(transactionId, amount, TransactionType.Income, categoryId, note, date);
                    break;
                case 4:
                    // Remove Transaction
                    System.out.println("Remove Transaction");
                    System.out.print("Enter transaction ID:");
                    transactionId = scanner.nextInt();
                    transactionManager.removeTransaction(transactionId);
                    break;
                case 5:
                    // View Transactions
                    System.out.println("View Transactions");
                    //Util.printTable(transactionManager.getTransactionList());
                    List<Transaction> transactionList = transactionManager.getTransactionList();
                    for (Transaction transaction : transactionList) {
                        System.out.println(transaction.toString());
                    }
                    break;
                case 6:
                    // Back
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void categoryMenu() {
        CategoryManager categoryManager = CategoryManager.getInstance();

        String categoryName;
        int categoryId;

        // Categories Menu Loop
        while(true){
            // Categories
            System.out.println("Categories Menu");
            System.out.println("\t1. Add Category");
            System.out.println("\t2. Edit Category");
            System.out.println("\t3. Remove Category");
            System.out.println("\t4. View Categories");
            System.out.println("\t5. Back");
            // Get user input
            System.out.print("Select an option:\t");
            int categoryOption = scanner.nextInt();
            // Switch case to handle user input
            switch (categoryOption) {
                case 1:
                    // Add Category
                    System.out.println("Add Category");
                    System.out.print("Enter category name:");
                    categoryName = scanner.next();
                    categoryManager.addCategory(categoryName);
                    break;
                case 2:
                    // Edit Category
                    System.out.println("Edit Category");
                    System.out.print("Enter category ID:");
                    categoryId = scanner.nextInt();
                    System.out.print("Enter category name:");
                    categoryName = scanner.next();
                    categoryManager.editCategory(categoryId, categoryName);
                    break;
                case 3:
                    // Remove Category
                    System.out.println("Remove Category");
                    System.out.print("Enter category ID:");
                    categoryId = scanner.nextInt();
                    categoryManager.removeCategory(categoryId);
                    break;
                case 4:
                    // View Categories
                    System.out.println("View Categories");
                    //Util.printTable(categoryManager.getCategoryList());
                    List<Category> categoryList = categoryManager.getCategoryList();
                    for (Category category : categoryList) {
                        System.out.println(category.toString());
                    }
                    break;
                case 5:
                    // Back
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static void budgetMenu() {
        BudgetManager budgetManager = BudgetManager.getInstance();

        int categoryId;
        double budgetAmount;

        // Budgets Menu Loop
        while(true){
            // Budgets
            System.out.println("Budgets Menu");
            System.out.println("\t1. Add Budget");
            System.out.println("\t2. Edit Budget");
            System.out.println("\t3. Remove Budget");
            System.out.println("\t4. View Budgets");
            System.out.println("\t5. Back");
            // Get user input
            System.out.print("Select an option:\t");
            int budgetOption = scanner.nextInt();
            // Switch case to handle user input
            switch (budgetOption) {
                case 1:
                    // Add Budget
                    System.out.println("Add Budget");
                    System.out.print("Enter category ID:");
                    categoryId = scanner.nextInt();
                    System.out.print("Enter budget amount:");
                    budgetAmount = scanner.nextDouble();
                    budgetManager.addBudget(categoryId, budgetAmount);
                    break;
                case 2:
                    // Edit Budget
                    System.out.println("Edit Budget");
                    System.out.print("Enter category ID:");
                    categoryId = scanner.nextInt();
                    System.out.print("Enter budget amount:");
                    budgetAmount = scanner.nextDouble();
                    budgetManager.setBudgetAmount(categoryId, budgetAmount);
                    break;
                case 3:
                    // Remove Budget
                    System.out.println("Remove Budget");
                    System.out.print("Enter category ID:");
                    categoryId = scanner.nextInt();
                    budgetManager.removeBudget(categoryId);
                    break;
                case 4:
                    // View Budgets
                    System.out.println("View Budgets");
                    //Util.printTable(budgetManager.getBudgets());
                    List<Budget> budgetList = budgetManager.getBudgets();
                    for (Budget budget : budgetList) {
                        System.out.println(budget.toString());
                    }
                    break;
                case 5:
                    // Back
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}
