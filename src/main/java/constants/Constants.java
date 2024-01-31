/**
 * Constants class contains all the constants used in the application.
 */
package constants;

public class Constants {
    // Menu Options
    public static final String MAIN_MENU = "\nMain Menu \n\t 1. Categories \n\t 2. Budgets \n\t 3. Transactions \n\t 4. Exit \n Select an option:\t ";
    public static final String CATEGORY_MENU = "\nCategory Menu \n\t 1. View All Categories \n\t 2. Find Category \n\t 3. Add Category \n\t 4. Update Categories \n\t 5. Remove Category \n\t 6. Back \n Select an option:\t ";
    public static final String BUDGET_MENU = "\nBudget Menu \n\t 1. View All Budgets \n\t 2. View All Budgets of a Category \n\t 3. View All Budgets Defined for a Month \n\t 4. Specify Budget for a Category \n\t 5. Updates Budget on a Category \n\t 6. Remove Budget of a Category \n\t 7. Back \n Select an option:\t ";
    public static final String TRANSACTION_MENU = "\nTransaction Menu \n\t 1. View All Transactions \n\t 2. View Category Specific Transactions \n\t 3. View Type Specific Transactions \n\t 4. Add Transaction \n\t 5. Update Transactions \n\t 6. Remove Transaction \n\t 7. Back \n Select an option:\t ";
    public static final String INVALID_OPTION = "Invalid option. Please try again.";

    // Error Messages
    public static final String ERROR_MESSAGE_BUDGET_NOT_FOUND = "Category not found for category ID: ";
    public static final String ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_ID = "Category not found for category ID: ";
    public static final String ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_NAME = "Category not found for category name: ";
    public static final String ERROR_MESSAGE_CATEGORY_FOUND_BY_NAME = "Category already existing with category name: ";
    public static final String ERROR_MESSAGE_EMPTY_CATEGORY = "Category list empty";
    public static final String ERROR_MESSAGE_PRINTABLE_CATEGORY_LIST_DOES_NOT_EXIST = "No printable category list";

    public static final String ERROR_MESSAGE_EMPTY_TRANSACTION = "Transaction list empty";
    public static final String ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_ID = "Transaction not found for transaction ID: ";
    public static final String ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_AMOUNT = "No transaction exists for the amount: ";
    public static final String ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_TYPE = "No transaction exists for the type: ";
    public static final String ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_CATEGORY_ID = "No transaction exists for the category id: ";
    public static final String ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_NOTE = "No transaction exists for the note: ";
    public static final String ERROR_MESSAGE_TRANSACTION_NOT_FOUND_BY_DATE = "No transaction exists for the date: ";
    public static final String ERROR_MESSAGE_PRINTABLE_TRANSACTION_LIST_DOES_NOT_EXIST = "No printable transaction list";

    public static final String ERROR_MESSAGE_EMPTY_BUDGET = "Budget list empty";
    public static final String ERROR_MESSAGE_BUDGETS_NOT_FOUND_BY_CATEGORY_ID = "No budget exists for the category id: ";
    public static final String ERROR_MESSAGE_BUDGETS_NOT_FOUND_BY_MONTH = "No budget defined for the month of: ";
    public static final String ERROR_MESSAGE_BUDGETS_NOT_FOUND_BY_AMOUNT = "No budget defined with the amount of: ";
    public static final String ERROR_MESSAGE_BUDGET_NOT_FOUND_BY_ID = "Budget not found for transaction ID: ";
    public static final String ERROR_MESSAGE_PRINTABLE_BUDGET_LIST_DOES_NOT_EXIST = "No printable budget list";
    public static final String ERROR_MESSAGE_BUDGET_ALREADY_EXIST = "A budget already exists for category ID and month";

    public static final String TITLE_DASHES = "----------------";

    public static final String TITLE_VIEW_CATEGORIES = "\n" + TITLE_DASHES + "View Categories" + TITLE_DASHES + "\n";
    public static final String TITLE_FIND_CATEGORY = "\n" + TITLE_DASHES + "Find Category" + TITLE_DASHES + "\n";
    public static final String TITLE_REMOVE_CATEGORY = "\n" + TITLE_DASHES + "Remove Category" + TITLE_DASHES + "\n";
    public static final String TITLE_UPDATE_CATEGORY = "\n" + TITLE_DASHES + "Update Category" + TITLE_DASHES + "\n";
    public static final String TITLE_ADD_CATEGORY = "\n" + TITLE_DASHES + "Add Category" + TITLE_DASHES + "\n";

    public static final String TITLE_VIEW_TRANSACTIONS = "\n" + TITLE_DASHES + "View Transactions" + TITLE_DASHES + "\n";
    public static final String TITLE_VIEW_CATEGORY_SPECIFIC_TRANSACTIONS = "\n" + TITLE_DASHES + "View Category Specific Transactions" + TITLE_DASHES + "\n";
    public static final String TITLE_VIEW_TYPE_SPECIFIC_TRANSACTIONS = "\n" + TITLE_DASHES + "View Transaction Type Specific Transactions" + TITLE_DASHES + "\n";
    public static final String TITLE_ADD_TRANSACTION = "\n" + TITLE_DASHES + "Add Transaction" + TITLE_DASHES + "\n";
    public static final String TITLE_UPDATE_TRANSACTION = "\n" + TITLE_DASHES + "Update Transaction" + TITLE_DASHES + "\n";
    public static final String TITLE_REMOVE_TRANSACTION = "\n" + TITLE_DASHES + "Remove Transaction" + TITLE_DASHES + "\n";

    public static final String TITLE_VIEW_BUDGETS = "\n" + TITLE_DASHES + "View Budgets" + TITLE_DASHES + "\n";
    public static final String TITLE_VIEW_CATEGORY_SPECIFIC_BUDGETS = "\n" + TITLE_DASHES + "View Budgets by Category" + TITLE_DASHES + "\n";
    public static final String TITLE_VIEW_MONTH_SPECIFIC_BUDGETS = "\n" + TITLE_DASHES + "View Budgets by Month" + TITLE_DASHES + "\n";
    public static final String TITLE_SPECIFY_BUDGET_ON_CATEGORY = "\n" + TITLE_DASHES + "Specify a Budget on a Category" + TITLE_DASHES + "\n";
    public static final String TITLE_UPDATE_BUDGET_BY_CATEGORY = "\n" + TITLE_DASHES + "Update Budget by Category" + TITLE_DASHES + "\n";
    public static final String TITLE_REMOVE_BUDGET_BY_ID = "\n" + TITLE_DASHES + "Remove Budget by ID" + TITLE_DASHES + "\n";

}
