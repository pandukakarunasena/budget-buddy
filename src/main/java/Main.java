import constants.Constants;
import constants.TransactionType;
import model.Category;
import model.Transaction;
import controllers.BudgetManager;
import controllers.CategoryManager;
import controllers.TransactionManager;
import util.Util;

import java.util.Date;
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
                    // Add Category
                    addCategory();
                    break;
                case 2:
                    // Edit Category
                    updateCategory();
                    break;
                case 3:
                    // Remove Category
                    removeCategory();
                    break;
                case 4:
                    // View Categories
                    viewCategories();
                    break;
                case 5:
                    // Find Category
                    findCategory();
                    break;
                case 6:
                    // Back
                    return;
                default:
                    System.out.println(Constants.INVALID_OPTION);
            }
        }
    }

    private static void addCategory() {
        CategoryManager categoryManager = new CategoryManager();

        try {
            System.out.println(Constants.TITLE_ADD_CATEGORY);
            System.out.print("Enter category name: ");
            String categoryName = scanner.next();

            categoryManager.addCategory(categoryName);
            System.out.println("Successfully added the category");
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
            System.out.println(categoryManager.getPrintableCategoryList());
            categoryId = getIntInput("Enter category ID:");
            System.out.print("Enter category name: ");
            categoryName = scanner.next();
            categoryManager.updateCategory(categoryId, categoryName);
            System.out.println("Successfully updated the category");
        } catch (Exception e) {
            System.out.println("Error updating category: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void removeCategory() {
        CategoryManager categoryManager = new CategoryManager();
        TransactionManager transactionManager = TransactionManager.getInstance();
        int categoryId = 0;
        List<Category> categoryList = null;
        boolean isCategoryExist = false;

        try {
            System.out.println(Constants.TITLE_REMOVE_CATEGORY);

            categoryManager.isCategoryNotEmpty();

            System.out.println(categoryManager.getPrintableCategoryList());

            categoryId = getIntInput("Enter category ID:");
            categoryManager.removeCategory(categoryId);
            System.out.println("Successfully removed the category");

            System.out.println("Removing all transactions under the category");
            transactionManager.removeAllTransactionsWithCategoryId(categoryId);
            System.out.println("Successfully removed the all transactions related to the category");
        } catch (Exception e) {
            System.out.println("Error removing category: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void viewCategories() {
        CategoryManager categoryManager = new CategoryManager();

        try {
            System.out.println(Constants.TITLE_VIEW_CATEGORIES);
            categoryManager.isCategoryNotEmpty();
            System.out.println(categoryManager.getPrintableCategoryList());
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

    private static void transactionMenu() {
        TransactionManager transactionManager = TransactionManager.getInstance();

        int transactionId;
        double amount;
        TransactionType transactionType;
        int categoryId;
        String note;
        Date date;

        while (true) {
            System.out.print(Constants.TRANSACTION_MENU);
            int transactionOption = scanner.nextInt();

            switch (transactionOption) {
                case 1:
                    // Add Income
                    addTransaction();
                    break;
                case 2:
                    // Edit Transaction
                    editTransaction();
                    break;
                case 3:
                    // Remove Transaction
                    removeTransaction();
                    break;
                case 4:
                    // View Transactions
                    viewTransactions();
                    break;
                case 5:
                    // Back
                    return;
                default:
                    System.out.println(Constants.INVALID_OPTION);
            }
        }
    }

    private static void addTransaction() {
        double amount;
        List<Category> categoryList = null;
        boolean isCategoryExist = false;
        int categoryId = 0;
        String note;
        Date date;
        TransactionManager transactionManager = TransactionManager.getInstance();
        CategoryManager categoryManager = new CategoryManager();
        int transactionTypeIn = 0;
        TransactionType transactionType = null;

        try {
            System.out.println("Add Transaction");

            categoryList = categoryManager.getCategoryList();
            if (categoryList != null && !categoryList.isEmpty()) {
                transactionTypeIn = getIntInput("Enter transaction type (1: Income, 2 or any other number: Expense):");
                transactionType = transactionTypeIn == 1 ? TransactionType.Income : TransactionType.Expense;
                amount = getDoubleInput("Enter amount:");

                Util.printList(categoryManager.getCategoryList());
                categoryId = getIntInput("Enter category ID:");
                isCategoryExist = categoryManager.isCategoryExistingById(categoryId);
                if (!isCategoryExist) {
                    System.out.println("Category with the given id does not exist");
                } else {
                    System.out.print("Enter note:");
                    note = scanner.next();

                    date = new Date();
                    transactionManager.addTransaction(amount, transactionType, categoryId, note, date);
                    System.out.println("Successfully added the transaction");
                }
            } else {
                System.out.println("No category available. Please add a category before continuing");
            }
        } catch (Exception e) {
            System.out.println("Error adding income: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void editTransaction() {
        int transactionId;
        double amount;
        int transactionTypeIn;
        TransactionType transactionType;
        int categoryId;
        String note;
        Date date;
        TransactionManager transactionManager = TransactionManager.getInstance();
        List<Transaction> transactionList = null;
        boolean isTransactionExist = false;
        List<Category> categoryList = null;
        CategoryManager categoryManager = new CategoryManager();
        boolean isCategoryExist = false;

        try {
            System.out.println("Edit Transaction");
            transactionList = transactionManager.getTransactionList();
            if (transactionList != null && !transactionList.isEmpty()) {
                Util.printList(transactionList);
                transactionId = getIntInput("Enter transaction ID:");
                isTransactionExist = transactionManager.isTransactionExisting(transactionId);
                if (!isTransactionExist) {
                    System.out.println("Transaction with the given id does not exist");
                } else {
                    categoryList = categoryManager.getCategoryList();
                    if (categoryList != null && !categoryList.isEmpty()) {
                        transactionTypeIn = getIntInput("Enter transaction type (1: Income, 2 or any other number: Expense):");
                        transactionType = transactionTypeIn == 1 ? TransactionType.Income : TransactionType.Expense;
                        amount = getDoubleInput("Enter amount:");

                        Util.printList(categoryManager.getCategoryList());
                        categoryId = getIntInput("Enter category ID:");
                        isCategoryExist = categoryManager.isCategoryExistingById(categoryId);
                        if (!isCategoryExist) {
                            System.out.println("Category with the given id does not exist");
                        } else {
                            System.out.print("Enter note:");
                            note = scanner.next();

                            date = new Date();
                            transactionManager.editTransaction(transactionId, amount, transactionType, categoryId, note, date);
                            System.out.println("Successfully updated the transaction");
                        }
                    } else {
                        System.out.println("No category available. Please add a category before continuing");
                    }
                }
            } else {
                System.out.println("No transaction available. Please add a transaction before attempting to update one");
            }
        } catch (Exception e) {
            System.out.println("Error updating transaction: " + e.getMessage());
            scanner.nextLine();
        }
    }

    private static void removeTransaction() {
        int transactionId;
        TransactionManager transactionManager = TransactionManager.getInstance();
        List<Transaction> transactionList = null;
        boolean isTransactionExist = false;

        try {
            System.out.println("Remove Transaction");
            transactionList = transactionManager.getTransactionList();
            if (transactionList != null && !transactionList.isEmpty()) {
                Util.printList(transactionList);
                transactionId = getIntInput("Enter transaction ID:");
                isTransactionExist = transactionManager.isTransactionExisting(transactionId);
                if (!isTransactionExist) {
                    System.out.println("Transaction with the given id does not exist");
                } else {
                    transactionManager.removeTransaction(transactionId);
                    System.out.println("Successfully removed the transaction");
                }
            } else {
                System.out.println("No transaction available. Please add a transaction before attempting to delete one");
            }
        } catch (Exception e) {
            System.out.println("Error updating transaction: " + e.getMessage());
            scanner.nextLine();
        }
    }

    public static void viewTransactions() {
        TransactionManager transactionManager = TransactionManager.getInstance();
        List<Transaction> transactionList = null;

        try {
            System.out.println("View Transactions");
            transactionList = transactionManager.getTransactionList();
            if (transactionList != null && !transactionList.isEmpty()) {
                Util.printList(transactionList);
            } else {
                System.out.println("No transaction available. Please add a transaction before continuing");
            }
        } catch (Exception e) {
            System.out.println("Error viewing transactions: " + e.getMessage());
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
