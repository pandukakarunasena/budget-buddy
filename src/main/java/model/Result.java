package model;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private final Map<String, Map<String, Double>> categoricalExpenditureResults;
    private double overallExpenditure = 0;
    private double overallBudget = 0;
    private double overallExpensePercentage = 0;

    public Result() {
        this.categoricalExpenditureResults = new HashMap<>();
    }

    public void addCategoryResult(String categoryName, double totalBudget, double totalExpenditure) {
        Map<String, Double> categoryResult = new HashMap<>();
        categoryResult.put("Total Budget", totalBudget);
        categoryResult.put("Total Expenditure", totalExpenditure);
        double expensePercentage = 0;

        this.overallExpenditure += totalExpenditure;
        this.overallBudget += totalBudget;
        if (totalBudget > 0) {
            expensePercentage = (totalExpenditure / totalBudget) * 100;
        } else if (totalBudget == 0) {
            expensePercentage = -1;
        }
        categoryResult.put("Expense Percentage", expensePercentage);

        categoricalExpenditureResults.put(categoryName, categoryResult);
    }

    public Map<String, Map<String, Double>> getCategoricalExpenditureResults() {
        return categoricalExpenditureResults;
    }

    public String getFormattedResults() {
        if (this.overallBudget > 0) {
            this.overallExpensePercentage = (this.overallExpenditure / this.overallBudget) * 100;
        }
        StringBuilder resultString = new StringBuilder();

        for (Map.Entry<String, Map<String, Double>> entry : categoricalExpenditureResults.entrySet()) {
            resultString.append("Category: ").append(entry.getKey()).append("\n");

            Map<String, Double> values = entry.getValue();
            for (Map.Entry<String, Double> valueEntry : values.entrySet()) {
                resultString.append("  ").append(valueEntry.getKey()).append(": ").append(valueEntry.getValue()).append("\n");
            }

            resultString.append("\n");
        }

        resultString.append("Overall Expenditure: ").append(this.overallExpenditure).append("\n");
        resultString.append("Overall Budget: ").append(this.overallBudget).append("\n");
        resultString.append("Overall Expense Percentage: ").append(this.overallExpensePercentage).append("%").append("\n");

        return resultString.toString();
    }
}
