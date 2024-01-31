package controllers;

import constants.Month;
import model.Budget;

import java.util.List;

public interface BudgetManager {
    List<Budget> getBudgetList();

    boolean isBudgetNotEmpty();

    Budget getBudgetById(int budgetId);

    boolean isBudgetExistingByBudgetId(int budgetId);

    List<Budget> getBudgetsByCategoryId(int categoryId);

    boolean doBudgetsExistByCategoryId(int categoryId);

    List<Budget> getBudgetsByMonth(Month month);

    boolean doBudgetsExistByMonth(Month month);

    List<Budget> getBudgetsByAmount(double amount);

    boolean doBudgetsExistByAmount(double amount);

    void addBudget(int categoryId, Month month, double amount);

    void updateBudget(int budgetId, int categoryId, Month month, double amount);

    void removeBudgetByBudgetId(int budgetId);

    void removeBudgetsByCategoryId(int categoryId);

    String getPrintableBudgetList(String filter, int id, Month month);

     boolean doesExpensesExceedBudget(int categoryId, Month month, double totalExpenses);
}
