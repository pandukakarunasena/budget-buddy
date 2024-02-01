package controllers;

import Util.Util;
import constants.Month;
import model.Budget;
import model.Category;
import model.Result;
import model.Transaction;
import services.TransactionDbServiceImpl;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ResultsManagerImpl implements ResultsManager {

    public ResultsManagerImpl() {
    }

    @Override
    public String getCategoricalAnalysis() {
        CategoryManager categoryManager = new CategoryManagerImpl();
        BudgetManager budgetManager = new BudgetManagerImpl();
        TransactionManager transactionManager = new TransactionManagerImpl();

        if (categoryManager.isCategoryNotEmpty()) {
            List<Category> categoryList = categoryManager.getCategoryList();
            Result result = new Result();
            for (Category category : categoryList) {
                int categoryId = category.getCategoryId();
                Budget budget = null;
                double budgetAmount = 0;
                double totalExpensesFroMonthInCategory = 0;
                try {
                    totalExpensesFroMonthInCategory = transactionManager.getTotalAmountForCategoryInCurrentMonth(categoryId);
                } catch (IllegalArgumentException ignored) {}

                try {
                    budget = budgetManager.getBudgetsByCategoryAndMonth(categoryId, Util.getCurrentMonthEnum());
                    budgetAmount = budget.getBudgetAmount();
                } catch (IllegalArgumentException ignored) {}

                result.addCategoryResult(category.getCategoryName(), budgetAmount, totalExpensesFroMonthInCategory);
            }
            if (!result.getCategoricalExpenditureResults().isEmpty()) {
                return result.getFormattedResults();
            }
        }
        return null;
    }
}
