/**
 * This class is used to manage and store the Budgets.
 */
package controllers;

import constants.Constants;
import constants.Month;
import model.Budget;
import services.BudgetDbService;
import services.BudgetDbServiceImpl;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class BudgetManagerImpl implements BudgetManager {
    private final BudgetDbService budgetDbService;
    private final AtomicInteger lastBudgetId;

    public BudgetManagerImpl() {
        this.budgetDbService = BudgetDbServiceImpl.getInstance();
        this.lastBudgetId = new AtomicInteger(getMaxBudgetId());
    }

    private int getMaxBudgetId() {
        List<Budget> budgetList = budgetDbService.getAllBudgets();
        return budgetList.stream()
                .mapToInt(Budget::getBudgetId)
                .max()
                .orElse(0);
    }

    @Override
    public List<Budget> getBudgetList() {
        List<Budget> budgetList = budgetDbService.getAllBudgets();
        if (budgetList != null && !budgetList.isEmpty()) {
            return budgetList;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_BUDGET);
        }
    }

    @Override
    public boolean isBudgetNotEmpty() {
        if (!budgetDbService.isBudgetEmpty()) {
            return true;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_BUDGET);
        }
    }

    @Override
    public Budget getBudgetById(int budgetId) {
        Optional<Budget> optionalBudget = budgetDbService.getBudgetById(budgetId);
        if (optionalBudget.isPresent()) {
            return optionalBudget.get();
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGET_NOT_FOUND_BY_ID + budgetId);
        }
    }

    @Override
    public boolean isBudgetExistingByBudgetId(int budgetId) {
        try {
            Budget returnedBudget = getBudgetById(budgetId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<Budget> getBudgetsByCategoryId(int categoryId) {
        List<Budget> budgets = budgetDbService.getBudgetsByCategoryId(categoryId);
        if (budgets != null && !budgets.isEmpty()) {
            return budgets;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGETS_NOT_FOUND_BY_CATEGORY_ID + categoryId);
        }
    }

    @Override
    public boolean doBudgetsExistByCategoryId(int categoryId) {
        try {
            List<Budget> returnedBudgets = getBudgetsByCategoryId(categoryId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<Budget> getBudgetsByMonth(Month month) {
        List<Budget> budgets = budgetDbService.getBudgetsByMonth(month);
        if (budgets != null && !budgets.isEmpty()) {
            return budgets;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGETS_NOT_FOUND_BY_MONTH + month);
        }
    }

    @Override
    public boolean doBudgetsExistByMonth(Month month) {
        try {
            List<Budget> returnedBudgets = getBudgetsByMonth(month);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public List<Budget> getBudgetsByAmount(double amount) {
        List<Budget> budgets = budgetDbService.getBudgetsByAmount(amount);
        if (budgets != null && !budgets.isEmpty()) {
            return budgets;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGETS_NOT_FOUND_BY_AMOUNT + amount);
        }
    }

    @Override
    public boolean doBudgetsExistByAmount(double amount) {
        try {
            List<Budget> returnedBudgets = getBudgetsByAmount(amount);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public void addBudget(int categoryId, Month month, double amount) {

        CategoryManager categoryManager = new CategoryManagerImpl();
        if (categoryManager.isCategoryExistingById(categoryId)) {
            List<Budget> budgets = budgetDbService.getBudgetsByCategoryIdAndMonth(categoryId, month);
            if (budgets == null || budgets.isEmpty()) {
                int newBudgetId = lastBudgetId.incrementAndGet();
                Budget budget = new Budget(newBudgetId, categoryId, month, amount);
                budgetDbService.addSingleBudget(budget);
            } else {
                throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGET_ALREADY_EXIST);
            }
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_ID + categoryId);
        }
    }

    @Override
    public void updateBudget(int budgetId, int categoryId, Month month, double amount) {
        CategoryManager categoryManager = new CategoryManagerImpl();
        System.out.println(categoryId);
        System.out.println(month);
        System.out.println(amount);

        if (categoryManager.isCategoryExistingById(categoryId)) {
            if (this.isBudgetNotEmpty()) {
                List<Budget> budgets = budgetDbService.getBudgetsByCategoryIdAndMonth(categoryId, month);
                if (budgets == null || budgets.isEmpty()) {
                    Budget budget = getBudgetById(budgetId);
                    if (budget != null) {
                        budget.setCategoryId(categoryId);
                        budget.setMonth(month);
                        budget.setBudgetAmount(amount);

                        budgetDbService.updateBudget(budget);
                    } else {
                        throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGET_NOT_FOUND_BY_ID + budgetId);
                    }
                } else {
                    throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGET_ALREADY_EXIST);
                }
            } else {
                throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_BUDGET);
            }
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_ID + categoryId);
        }
    }

    @Override
    public void removeBudgetByBudgetId(int budgetId) {
        if (isBudgetExistingByBudgetId(budgetId)) {
            budgetDbService.removeBudgetByBudgetId(budgetId);
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGET_NOT_FOUND_BY_ID + budgetId);
        }
    }

    @Override
    public void removeBudgetsByCategoryId(int categoryId) {
        if (doBudgetsExistByCategoryId(categoryId)) {
            budgetDbService.removeBudgetByCategoryId(categoryId);
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGETS_NOT_FOUND_BY_CATEGORY_ID + categoryId);
        }
    }

    @Override
    public String getPrintableBudgetList(String filter, int id, Month month) {
        List<Budget> budgetList = null;
        if (filter.equals("all")) {
            budgetList = budgetDbService.getAllBudgets();
        } else if (filter.equals("filter-category") && id > 0) {
            budgetList = budgetDbService.getBudgetsByCategoryId(id);
        } else if (filter.equals("filter-month")) {
            budgetList = budgetDbService.getBudgetsByMonth(month);
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_PRINTABLE_BUDGET_LIST_DOES_NOT_EXIST);
        }

        if (budgetList != null && !budgetList.isEmpty()) {
            StringBuilder result = new StringBuilder();
            result.append("Budget ID\tCategory ID\tMonth\tBudget\n");

            for (Budget budget : budgetList) {
                result.append(budget.getBudgetId())
                        .append("\t\t")
                        .append(budget.getCategoryId())
                        .append("\t\t")
                        .append(budget.getMonth())
                        .append("\t\t")
                        .append(budget.getBudgetAmount())
                        .append("\n");
            }

            return result.toString();
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_BUDGET);
        }
    }

    public boolean doesExpensesExceedBudget(int categoryId, Month month, double totalExpenses) {
        List<Budget> budgets = budgetDbService.getBudgetsByCategoryIdAndMonth(categoryId, month);
        if (budgets.size() == 1) {
            return totalExpenses > budgets.get(0).getBudgetAmount();
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_BUDGET_NO_EXIST);
        }
    }
}