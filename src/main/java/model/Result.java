package model;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private final Map<String, Map<String, Double>> categoricalExpenditureResults;

    public Result() {
        this.categoricalExpenditureResults = new HashMap<>();
    }

    public void addCategoryResult(String categoryName, double totalBudget, double totalExpenditure) {
        Map<String, Double> categoryResult = new HashMap<>();
        categoryResult.put("Total Budget", totalBudget);
        categoryResult.put("Total Expenditure", totalExpenditure);
        double expensePercentage = 0;
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
        StringBuilder resultString = new StringBuilder();

        for (Map.Entry<String, Map<String, Double>> entry : categoricalExpenditureResults.entrySet()) {
            resultString.append("Category: ").append(entry.getKey()).append("\n");

            Map<String, Double> values = entry.getValue();
            for (Map.Entry<String, Double> valueEntry : values.entrySet()) {
                resultString.append("  ").append(valueEntry.getKey()).append(": ").append(valueEntry.getValue()).append("\n");
            }

            resultString.append("\n");
        }

        return resultString.toString();
    }
}
