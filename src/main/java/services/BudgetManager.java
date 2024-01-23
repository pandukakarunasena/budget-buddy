package services;

import model.Budget;

import java.util.*;

// Singleton class
public class BudgetManager {
    private final List<Budget> budgetList;
    
    private static BudgetManager instance;

    private BudgetManager() {
        this.budgetList = new ArrayList<>();
    }

    public static BudgetManager getInstance() {
        if (instance == null) {
            instance = new BudgetManager();
        }
        return instance;
    }

    // Add Budget
    public void addBudget(int categoryId, double budgetAmount) {
        Budget budget = new Budget(categoryId, budgetAmount);
        // TODO: return exception if category already has budget
        budgetList.add(budget);
    }

    // Get Budget from Category ID
    public Budget getBudget(int categoryId) {
        return budgetList.get(categoryId);
    }

    // Get Budget Amount from Category ID
    public double getBudgetAmount(int categoryId) {
        return getBudget(categoryId).getBudgetAmount();
    }

    // Set Budget Amount from Category ID
    public void setBudgetAmount(int categoryId, double budgetAmount) {
        getBudget(categoryId).setBudgetAmount(budgetAmount);
    }

    // Remove Budget from Category ID
    public void removeBudget(int categoryId) {
        budgetList.remove(categoryId);
    }

    // Get Budgets List
    public List<Budget> getBudgets() {
        return budgetList;
    }
}

