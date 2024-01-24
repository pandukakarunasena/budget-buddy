/**
 * This class is used to manage and store the Budgets.
 */
package services;

import constants.Constants;
import model.Budget;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

// Singleton class
public final class BudgetManager {
    /** Budget List. */
    private final List<Budget> budgetList;

    /** BudgetManager instance. */
    private static BudgetManager instance;

    /**
     * Constructor for BudgetManager.
     */
    private BudgetManager() {
        this.budgetList = new ArrayList<>();
    }

    /**
     * Get BudgetManager instance.
     *
     * @return BudgetManager instance
     */
    public static BudgetManager getInstance() {
        if (instance == null) {
            instance = new BudgetManager();
        }
        return instance;
    }

    /**
     * Add Budget.
     *
     * @param categoryId   Category ID
     * @param budgetAmount Budget Amount
     */
    public void addBudget(int categoryId, double budgetAmount) {
        for (Budget budget: budgetList) {
            if (budget.getCategoryId() == categoryId) {
                throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGET_ALREADY_EXIST + categoryId);
            }
        }
        Budget budget = new Budget(categoryId, budgetAmount);
        budgetList.add(budget);
    }

    /**
     * Get Budget from Category ID.
     *
     * @param categoryId Category ID
     * @return Budget
     */
    public Budget getBudget(int categoryId) {
        for (Budget budget: budgetList) {
            if (budget.getCategoryId() == categoryId) {
                return budget;
            }
        }
        throw new NoSuchElementException(Constants.ERROR_MESSAGE_BUDGET_NOT_FOUND + categoryId);
    }

    /**
     * Get Budget Amount from Category ID.
     *
     * @param categoryId Category ID
     * @return Budget Amount
     */
    public double getBudgetAmount(int categoryId) {
        return getBudget(categoryId).getBudgetAmount();
    }

    /**
     * Set Budget Amount from Category ID.
     *
     * @param categoryId   Category ID
     * @param budgetAmount Budget Amount
     */
    public void setBudgetAmount(int categoryId, double budgetAmount) {
        getBudget(categoryId).setBudgetAmount(budgetAmount);
    }

    /**
     * Remove Budget from Category ID.
     *
     * @param categoryId Category ID
     */
    public void removeBudget(int categoryId) {
        budgetList.remove(getBudget(categoryId));
    }

    /**
     * Get Budget List.
     *
     * @return Budget List
     */
    public List<Budget> getBudgets() {
        return budgetList;
    }
}

