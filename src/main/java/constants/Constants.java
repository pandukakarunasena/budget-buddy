/**
 * Constants class contains all the constants used in the application.
 */
package constants;

public class Constants {
    // Menu Options
    public static final String MAIN_MENU = "\nMain Menu \n\t 1. Categories \n\t 2. Budgets \n\t 3. Transactions \n\t 4. Exit \n Select an option:\t ";
    public static final String TRANSACTION_MENU = "Transaction Menu \n\t 1. Add Transaction \n\t 2. Update Transaction \n\t 3. Delete Transaction \n\t 4. View Transactions \n\t 5. Back \n Select an option:\t ";
    public static final String CATEGORY_MENU = "\nCategory Menu \n\t 1. Add Category \n\t 2. Update Category \n\t 3. Delete Category \n\t 4. View Categories \n\t 5. Find Category \n\t 6. Back \n Select an option:\t ";
    public static final String BUDGET_MENU = "\nBudget Menu \n\t 1. Add Budget \n\t 2. Edit Budget \n\t 3. Delete Budget \n\t 4. View Budgets \n\t 5. Back \n Select an option:\t ";
    public static final String INVALID_OPTION = "Invalid option. Please try again.";

    // Error Messages
    public static final String ERROR_MESSAGE_BUDGET_ALREADY_EXIST = "Budget already exists for category ID: ";
    public static final String ERROR_MESSAGE_BUDGET_NOT_FOUND = "Category not found for category ID: ";
    public static final String ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_ID = "Category not found for category ID: ";
    public static final String ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_NAME = "Category not found for category name: ";
    public static final String ERROR_MESSAGE_CATEGORY_FOUND_BY_NAME = "Category already existing with category name: ";
    public static final String ERROR_MESSAGE_TRANSACTION_NOT_FOUND = "Transaction not found for transaction ID: ";
    public static final String ERROR_MESSAGE_EMPTY_CATEGORY = "Category list empty";

    public static final String TITLE_DASHES = "----------------";
    public static final String TITLE_VIEW_CATEGORIES = "\n" + TITLE_DASHES + "View Categories" + TITLE_DASHES + "\n";
    public static final String TITLE_FIND_CATEGORY = "\n" + TITLE_DASHES + "Find Category" + TITLE_DASHES + "\n";
    public static final String TITLE_REMOVE_CATEGORY = "\n" + TITLE_DASHES + "Remove Category" + TITLE_DASHES + "\n";
    public static final String TITLE_UPDATE_CATEGORY = "\n" + TITLE_DASHES + "Update Category" + TITLE_DASHES + "\n";
    public static final String TITLE_ADD_CATEGORY = "\n" + TITLE_DASHES + "Add Category" + TITLE_DASHES + "\n";

}
