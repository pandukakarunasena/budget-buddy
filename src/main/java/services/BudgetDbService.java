package services;

import constants.Month;
import model.Budget;

import java.util.List;
import java.util.Optional;

public interface BudgetDbService {

    List<Budget> getAllBudgets();

    boolean isBudgetEmpty();

    Optional<Budget> getBudgetById(int budgetId);

    List<Budget> getBudgetsByCategoryId(int categoryId);

    List<Budget> getBudgetsByMonth(Month month);

    List<Budget> getBudgetsByCategoryIdAndMonth(int categoryId, Month month);

    List<Budget> getBudgetsByAmount(double amount);

    void addAllBudgets(List<Budget> budgets);

    void addSingleBudget(Budget budget);

    void updateBudget(Budget updatedBudget);

    void removeBudgetByBudgetId(int budgetId);

    void removeBudgetByCategoryId(int categoryId);

    void removeAllBudgets();
}
