/**
 * Constants class contains all the constants used in the application.
 */
package constants;

public class Constants {
    // Menu Options
    public static final String MAIN_MENU = "Main Menu \n\t 1. Categories \n\t 2. Budgets \n\t 3. Transactions \n\t 4. Exit \n Select an option:\t ";
    public static final String TRANSACTION_MENU = "Transaction Menu \n\t 1. Add Transaction \n\t 2. Edit Transaction \n\t 3. Delete Transaction \n\t 4. View Transactions \n\t 5. Back \n Select an option:\t ";
    public static final String CATEGORY_MENU = "Category Menu \n\t 1. Add Category \n\t 2. Edit Category \n\t 3. Delete Category \n\t 4. View Categories \n\t 5. Back \n Select an option:\t ";
    public static final String BUDGET_MENU = "Budget Menu \n\t 1. Add Budget \n\t 2. Edit Budget \n\t 3. Delete Budget \n\t 4. View Budgets \n\t 5. Back \n Select an option:\t ";
    public static final String INVALID_OPTION = "Invalid option. Please try again.";

    // Error Messages
    public static final String ERROR_MESSAGE_BUDGET_ALREADY_EXIST = "Budget already exists for category ID: ";
    public static final String ERROR_MESSAGE_BUDGET_NOT_FOUND = "Category not found for category ID: ";
    public static final String ERROR_MESSAGE_CATEGORY_NOT_FOUND = "Category not found for category ID: ";
    public static final String ERROR_MESSAGE_TRANSACTION_NOT_FOUND = "Transaction not found for transaction ID: ";
}
