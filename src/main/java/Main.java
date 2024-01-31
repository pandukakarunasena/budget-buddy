import constants.Constants;
import constants.TransactionType;
import model.Category;
import model.Transaction;
import controllers.BudgetManager;
import controllers.CategoryManager;
import controllers.TransactionManager;
import util.Util;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print(Constants.MAIN_MENU);
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    categoryMenu();
                    break;
                case 2:
                    budgetMenu();
                    break;
                case 3:
                    transactionMenu();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println(Constants.INVALID_OPTION);
            }
        }
    }

    private static void categoryMenu() {
        while (true) {
            System.out.print(Constants.CATEGORY_MENU);
            int categoryOption = scanner.nextInt();
            switch (categoryOption) {
                case 1:
                    // View Categories
                    viewCategories();
                    break;
                case 2:
                    // Find Category
                    findCategory();
                    break;
                case 3:
                    // Add Category
                    addCategory();
                    break;
                case 4:
                    // Edit Category
                    updateCategory();
                    break;
                case 5:
                    // Remove Category
                    removeCategory();
                    break;
                case 6:
                    // Back
                    return;
                default:
                    System.out.println(Constants.INVALID_OPTION);
            }
        }
    }

    public static void viewCategories() {
        CategoryManager categoryManager = new CategoryManager();

        try {
            System.out.println(Constants.TITLE_VIEW_CATEGORIES);
            categoryManager.isCategoryNotEmpty();
            System.out.println(categoryManager.getPrintableCategoryList("all"));
        } catch (Exception e) {
            System.out.println("Error viewing categories: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void findCategory() {
        CategoryManager categoryManager = new CategoryManager();

        try {
            System.out.println(Constants.TITLE_FIND_CATEGORY);
            categoryManager.isCategoryNotEmpty();
            System.out.print("Enter category name: ");
            String categoryName = scanner.next();
            Category category = categoryManager.getCategoryByName(categoryName);
            System.out.println(category.toString());
        } catch (Exception e) {
            System.out.println("Error viewing categories: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void addCategory() {
        CategoryManager categoryManager = new CategoryManager();

        try {
            System.out.println(Constants.TITLE_ADD_CATEGORY);
            System.out.print("Enter category name: ");
            String categoryName = scanner.next();

            categoryManager.addCategory(categoryName);
            System.out.println("Category added successfully");
        } catch (Exception e) {
            System.out.println("Error adding category: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void updateCategory() {
        CategoryManager categoryManager = new CategoryManager();
        String categoryName;
        int categoryId = 0;
        List<Category> categoryList = null;
        boolean isCategoryExist = false;

        try {
            System.out.println(Constants.TITLE_UPDATE_CATEGORY);
            categoryManager.isCategoryNotEmpty();
            System.out.println(categoryManager.getPrintableCategoryList("all"));
            categoryId = getIntInput("Enter category ID: ");
            System.out.print("Enter category name: ");
            categoryName = scanner.next();
            categoryManager.updateCategory(categoryId, categoryName);
            System.out.println("Category updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating category: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void removeCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TransactionManager transactionManager = new TransactionManager();
        int categoryId = 0;
        List<Category> categoryList = null;
        boolean isCategoryExist = false;

        try {
            System.out.println(Constants.TITLE_REMOVE_CATEGORY);

            categoryManager.isCategoryNotEmpty();

            System.out.println(categoryManager.getPrintableCategoryList("all"));

            categoryId = getIntInput("Enter category ID: ");
            categoryManager.removeCategory(categoryId);
            System.out.println("Category removed successfully");

            System.out.println("Removing all transactions under the category");
            transactionManager.removeTransactionsByCategoryId(categoryId);
            System.out.println("Successfully removed the all transactions related to the category");
        } catch (Exception e) {
            System.out.println("Error removing category: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void transactionMenu() {
        while (true) {
            System.out.print(Constants.TRANSACTION_MENU);
            int transactionOption = scanner.nextInt();

            switch (transactionOption) {
                case 1:
                    // View Transactions
                    viewAllTransactions();
                    break;
                case 2:
                    // View Transactions By Category
                    viewTransactionsByCategory();
                    break;
                case 3:
                    // Vie Transaction By Type
                    viewTransactionsByTransactionType();
                    break;
                case 4:
                    // Add Transaction
                    addTransaction();
                    break;
                case 5:
                    // Update Transaction
                    updateTransaction();
                    break;
                case 6:
                    // Remove Transaction
                    removeTransaction();
                    break;
                case 7:
                    // Back
                    return;
                default:
                    System.out.println(Constants.INVALID_OPTION);
            }
        }
    }

    public static void viewAllTransactions() {
        TransactionManager transactionManager = new TransactionManager();

        try {
            System.out.println(Constants.TITLE_VIEW_TRANSACTIONS);
            transactionManager.isTransactionNotEmpty();
            System.out.println(transactionManager.getPrintableTransactionList("all", 0));
        } catch (Exception e) {
            System.out.println("Error viewing transactions: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void viewTransactionsByCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TransactionManager transactionManager = new TransactionManager();

        try {
            System.out.println(Constants.TITLE_VIEW_CATEGORY_SPECIFIC_TRANSACTIONS);
            System.out.println(categoryManager.getPrintableCategoryList("all"));
            int categoryId = getIntInput("Enter category id to filter transactions by: ");
            if (transactionManager.isTransactionsExistingByCategoryId(categoryId))
            {
                System.out.println(transactionManager.getPrintableTransactionList("filter-category", categoryId));
                // CHARTER TIKAK
            } else {
                List<Transaction> transactions = transactionManager.getTransactionsByCategoryId(categoryId);
                // CHARTER TIKAK
            };
        } catch (Exception e) {
            System.out.println("Error viewing transactions: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void viewTransactionsByTransactionType() {
        CategoryManager categoryManager = new CategoryManager();
        TransactionManager transactionManager = new TransactionManager();
        int transactionTypeIn = 0;
        TransactionType transactionType = null;

        try {
            System.out.println(Constants.TITLE_VIEW_TYPE_SPECIFIC_TRANSACTIONS);
            transactionTypeIn = getIntInput("Enter transaction type (1: Income, Any other number: Expense): ");
            transactionType = transactionTypeIn == 1 ? TransactionType.Income : TransactionType.Expense;
            if (transactionManager.isTransactionsExistingByType(transactionType))
            {
                System.out.println(transactionManager.getPrintableTransactionList("filter-type", transactionTypeIn));
                // CHARTER GODAK
            } else {
                List<Transaction> transactions = transactionManager.getTransactionsByType(transactionType);
                // CHARTER TIKAK
            };
        } catch (Exception e) {
            System.out.println("Error viewing transactions: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void addTransaction() {
        CategoryManager categoryManager = new CategoryManager();
        int categoryId = 0;
        int transactionTypeIn = 0;
        TransactionType transactionType = null;
        double amount = 0;
        String note = null;
        TransactionManager transactionManager = new TransactionManager();

        try {
            System.out.println(Constants.TITLE_ADD_TRANSACTION);

            System.out.println(categoryManager.getPrintableCategoryList("all"));
            categoryId = getIntInput("Enter category ID: ");
            transactionTypeIn = getIntInput("Enter transaction type (1: Income, Any other number: Expense): ");
            transactionType = transactionTypeIn == 1 ? TransactionType.Income : TransactionType.Expense;
            amount = getDoubleInput("Enter amount: ");
            System.out.print("Enter note: ");
            note = scanner.next();

            transactionManager.addTransaction(amount, transactionType, categoryId, note);
            System.out.println("Transaction added successfully");
        } catch (Exception e) {
            System.out.println("Error adding transaction: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void updateTransaction() {
        TransactionManager transactionManager = new TransactionManager();
        int transactionId = 0;
        CategoryManager categoryManager = new CategoryManager();
        int categoryId = 0;
        int transactionTypeIn = 0;
        TransactionType transactionType = null;
        double amount = 0;
        String note = null;

        try {
            System.out.println(Constants.TITLE_UPDATE_TRANSACTION);
            transactionManager.isTransactionNotEmpty();
            System.out.println(transactionManager.getPrintableTransactionList("all", 0));
            transactionId = getIntInput("Enter transaction ID:");
            System.out.println(categoryManager.getPrintableCategoryList("all"));
            categoryId = getIntInput("Enter category ID: ");
            transactionTypeIn = getIntInput("Enter transaction type (1: Income, Any other number: Expense): ");
            transactionType = transactionTypeIn == 1 ? TransactionType.Income : TransactionType.Expense;
            amount = getDoubleInput("Enter amount: ");
            System.out.print("Enter note: ");
            note = scanner.next();

            transactionManager.updateTransaction(transactionId, amount, transactionType, categoryId, note);
            System.out.println("Transaction updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating transaction: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void removeTransaction() {
        TransactionManager transactionManager = new TransactionManager();
        int transactionId = 0;
        try {
            System.out.println(Constants.TITLE_REMOVE_TRANSACTION);
            transactionManager.isTransactionNotEmpty();
            System.out.println(transactionManager.getPrintableTransactionList("all", 0));
            transactionId = getIntInput("Enter transaction ID:");

            transactionManager.removeTransactionByTransactionId(transactionId);
            System.out.println("Transactions removed successfully");
        } catch (Exception e) {
            System.out.println("Error removing transaction: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next();
            }
        }
    }

    public static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next();
            }
        }
    }












    private static void budgetMenu() {
        BudgetManager budgetManager = BudgetManager.getInstance();

        int categoryId;
        double budgetAmount;

        while (true) {
            System.out.print(Constants.BUDGET_MENU);
            int budgetOption = scanner.nextInt();

            switch (budgetOption) {
                case 1:
                    // Add Budget
                    System.out.println("Add Budget");
                    System.out.print("Enter category ID: ");
                    categoryId = scanner.nextInt();
                    System.out.print("Enter budget amount: ");
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
                    Util.printList(budgetManager.getBudgets());
                    break;
                case 5:
                    // Back
                    return;
                default:
                    System.out.println(Constants.INVALID_OPTION);
            }
        }
    }
}
