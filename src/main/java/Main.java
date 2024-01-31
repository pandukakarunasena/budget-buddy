import constants.Constants;
import constants.Month;
import constants.TransactionType;
import controllers.*;
import model.Budget;
import model.Category;
import model.Transaction;

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

    private static void viewCategories() {
        CategoryManager categoryManager = new CategoryManagerImpl();

        try {
            System.out.println(Constants.TITLE_VIEW_CATEGORIES);
            categoryManager.isCategoryNotEmpty();
            System.out.println(categoryManager.getPrintableCategoryList("all"));
        } catch (Exception e) {
            System.out.println("Error viewing categories: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void findCategory() {
        CategoryManager categoryManager = new CategoryManagerImpl();

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
        CategoryManager categoryManager = new CategoryManagerImpl();

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
        CategoryManager categoryManager = new CategoryManagerImpl();
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

    private static void removeCategory() {
        CategoryManager categoryManager = new CategoryManagerImpl();
        TransactionManager transactionManager = new TransactionManagerImpl();
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
        TransactionManager transactionManager = new TransactionManagerImpl();

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
        CategoryManager categoryManager = new CategoryManagerImpl();
        TransactionManager transactionManager = new TransactionManagerImpl();

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
        TransactionManager transactionManager = new TransactionManagerImpl();
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
        CategoryManager categoryManager = new CategoryManagerImpl();
        int categoryId = 0;
        int transactionTypeIn = 0;
        TransactionType transactionType = null;
        double amount = 0;
        String note = null;
        TransactionManager transactionManager = new TransactionManagerImpl();

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
        TransactionManager transactionManager = new TransactionManagerImpl();
        int transactionId = 0;
        CategoryManager categoryManager = new CategoryManagerImpl();
        int categoryId = 0;
        int transactionTypeIn = 0;
        TransactionType transactionType = null;
        double amount = 0;
        String note = null;

        try {
            System.out.println(Constants.TITLE_UPDATE_TRANSACTION);
            transactionManager.isTransactionNotEmpty();
            System.out.println(transactionManager.getPrintableTransactionList("all", 0));
            transactionId = getIntInput("Enter transaction ID: ");
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
        TransactionManager transactionManager = new TransactionManagerImpl();
        int transactionId = 0;
        try {
            System.out.println(Constants.TITLE_REMOVE_TRANSACTION);
            transactionManager.isTransactionNotEmpty();
            System.out.println(transactionManager.getPrintableTransactionList("all", 0));
            transactionId = getIntInput("Enter transaction ID: ");

            transactionManager.removeTransactionByTransactionId(transactionId);
            System.out.println("Transactions removed successfully");
        } catch (Exception e) {
            System.out.println("Error removing transaction: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void budgetMenu() {

        while (true) {
            System.out.print(Constants.BUDGET_MENU);
            int budgetOption = scanner.nextInt();

            switch (budgetOption) {
                case 1:
                    // View all budgets
                    viewAllBudgets();
                    break;
                case 2:
                    // View budgets by category
                    viewBudgetsByCategory();
                    break;
                case 3:
                    // View budgets by Month
                    viewBudgetsByMonth();
                    break;
                case 4:
                    // Add budget
                    specifyBudgetByCategory();
                    break;
                case 5:
                    // Update budget on a category
                    updateBudget();
                    break;
                case 6:
                    // Delete budget
                    removeBudgetById();
                    break;
                case 7:
                    // Back
                    return;
                default:
                    System.out.println(Constants.INVALID_OPTION);
            }
        }
    }

    public static void viewAllBudgets() {
        BudgetManager budgetManager = new BudgetManagerImpl();

        try {
            System.out.println(Constants.TITLE_VIEW_BUDGETS);
            budgetManager.isBudgetNotEmpty();
            System.out.println(budgetManager.getPrintableBudgetList("all", 0, null));
        } catch (Exception e) {
            System.out.println("Error viewing budgets: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void viewBudgetsByCategory() {
        CategoryManager categoryManager = new CategoryManagerImpl();
        BudgetManager budgetManager = new BudgetManagerImpl();

        try {
            System.out.println(Constants.TITLE_VIEW_CATEGORY_SPECIFIC_BUDGETS);
            System.out.println(categoryManager.getPrintableCategoryList("all"));
            int categoryId = getIntInput("Enter category id to filter budgets by: ");
            if (budgetManager.doBudgetsExistByCategoryId(categoryId))
            {
                System.out.println(budgetManager.getPrintableBudgetList("filter-category", categoryId, null));
                // CHARTER TIKAK
            } else {
                List<Budget> budgets = budgetManager.getBudgetsByCategoryId(categoryId);
                // CHARTER TIKAK
            };
        } catch (Exception e) {
            System.out.println("Error viewing budgets by category: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void viewBudgetsByMonth() {
        CategoryManager categoryManager = new CategoryManagerImpl();
        BudgetManager budgetManager = new BudgetManagerImpl();

        try {
            System.out.println(Constants.TITLE_VIEW_MONTH_SPECIFIC_BUDGETS);
            for (Month month : Month.values()) {
                System.out.println(month);
            }
            System.out.println("Enter month to filter budgets by: ");
            String monthIn = scanner.next();

            Month selectedMonth = Month.valueOf(monthIn);
            if (budgetManager.doBudgetsExistByMonth(selectedMonth))
            {
                System.out.println(budgetManager.getPrintableBudgetList("filter-month", -1, selectedMonth));
                // CHARTER TIKAK
            } else {
                List<Budget> budgets = budgetManager.getBudgetsByMonth(selectedMonth);
                // CHARTER TIKAK
            };
        } catch (Exception e) {
            System.out.println("Error viewing budgets by month: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void specifyBudgetByCategory() {
        CategoryManager categoryManager = new CategoryManagerImpl();
        int categoryId = 0;
        BudgetManager budgetManager = new BudgetManagerImpl();
        double amount = 0;

        try {
            System.out.println(Constants.TITLE_SPECIFY_BUDGET_ON_CATEGORY);

            System.out.println(categoryManager.getPrintableCategoryList("all"));
            categoryId = getIntInput("Enter category ID: ");
            for (Month month : Month.values()) {
                System.out.println(month);
            }
            System.out.println("Enter month of the budget: ");
            String monthIn = scanner.next();

            Month selectedMonth = Month.valueOf(monthIn);
            amount = getDoubleInput("Enter budget amount for the month of " + monthIn + " : ");
            budgetManager.addBudget(categoryId, selectedMonth, amount);
            System.out.println("Budget specified successfully");
        } catch (Exception e) {
            System.out.println("Error specifying budget by category: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void updateBudget() {
        BudgetManager budgetManager = new BudgetManagerImpl();
        CategoryManager categoryManager = new CategoryManagerImpl();
        int categoryId = 0;
        int budgetId = 0;
        double amount = 0;

        try {
            System.out.println(Constants.TITLE_UPDATE_BUDGET_BY_CATEGORY);

            budgetManager.isBudgetNotEmpty();
            System.out.println(budgetManager.getPrintableBudgetList("all", 0, null));
            budgetId = getIntInput("Select budget ID to update: ");
            System.out.println(categoryManager.getPrintableCategoryList("all"));
            categoryId = getIntInput("Enter category ID: ");
            for (Month month : Month.values()) {
                System.out.println(month);
            }
            System.out.println("Enter month of the budget: ");
            String monthIn = scanner.next();
            Month selectedMonth = Month.valueOf(monthIn);
            amount = getDoubleInput("Enter budget amount for the month of " + monthIn + " : ");
            budgetManager.updateBudget(budgetId, categoryId, selectedMonth, amount);
            System.out.println("Budget updated successfully");
        } catch (Exception e) {
            System.out.println("Error updating budget: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void removeBudgetById() {
        BudgetManager budgetManager = new BudgetManagerImpl();
        int budgetId = 0;

        try {
            System.out.println(Constants.TITLE_REMOVE_BUDGET_BY_ID);

            budgetManager.isBudgetNotEmpty();
            System.out.println(budgetManager.getPrintableBudgetList("all", 0, null));
            budgetId = getIntInput("Select budget ID: ");
            budgetManager.removeBudgetByBudgetId(budgetId);
            System.out.println("Budget removed successfully");
        } catch (Exception e) {
            System.out.println("Error updating budget: " + e.getMessage());
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
}
