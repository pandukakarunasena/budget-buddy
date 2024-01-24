import constants.Constants;
import constants.TransactionType;
import model.Budget;
import model.Category;
import model.Transaction;
import services.BudgetManager;
import services.CategoryManager;
import services.TransactionManager;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.print(Constants.MAIN_MENU);
            int option = scanner.nextInt();

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
                    System.out.println(Constants.INVALID_OPTION);
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

        while(true){
            System.out.print(Constants.TRANSACTION_MENU);
            int transactionOption = scanner.nextInt();

            switch (transactionOption) {
                case 1:
                    // Add Income
                    System.out.println("Add Income Transaction");
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
                    // Add Expense
                    System.out.println("Add Expense Transaction");
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
                    List<Transaction> transactionList = transactionManager.getTransactionList();
                    for (Transaction transaction : transactionList) {
                        System.out.println(transaction.toString());
                    }
                    break;
                case 6:
                    // Back
                    return;
                default:
                    System.out.println(Constants.INVALID_OPTION);
            }
        }
    }

    private static void categoryMenu() {
        CategoryManager categoryManager = CategoryManager.getInstance();

        String categoryName;
        int categoryId;

        while(true){
            System.out.print(Constants.CATEGORY_MENU);
            int categoryOption = scanner.nextInt();

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
                    List<Category> categoryList = categoryManager.getCategoryList();
                    for (Category category : categoryList) {
                        System.out.println(category.toString());
                    }
                    break;
                case 5:
                    // Back
                    return;
                default:
                    System.out.println(Constants.INVALID_OPTION);
            }
        }
    }

    private static void budgetMenu() {
        BudgetManager budgetManager = BudgetManager.getInstance();

        int categoryId;
        double budgetAmount;

        while(true){
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
                    List<Budget> budgetList = budgetManager.getBudgets();
                    for (Budget budget : budgetList) {
                        System.out.println(budget.toString());
                    }
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
