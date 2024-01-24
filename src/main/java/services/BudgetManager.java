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
        for(Budget budget: budgetList) {
            if(budget.getCategoryId() == categoryId) {
                throw new IllegalArgumentException("Budget already exists for category ID: " + categoryId);
            }
        }
        Budget budget = new Budget(categoryId, budgetAmount);
        budgetList.add(budget);
    }

    // Get Budget from Category ID
    public Budget getBudget(int categoryId) {
        for(Budget budget: budgetList) {
            if(budget.getCategoryId() == categoryId) {
                return budget;
            }
        }
        throw new NoSuchElementException("Budget not found for category ID: " + categoryId);
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
        budgetList.remove(getBudget(categoryId));
    }

    // Get Budgets List
    public List<Budget> getBudgets() {
        return budgetList;
    }
}

