/**
 * Model class for Budget.
 */
package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import constants.Month;

public class Budget {
    private final int budgetId;
    private int categoryId;
    private Month month;
    private double budgetAmount;

    @JsonCreator
    public Budget(
            @JsonProperty("budgetId") int budgetId,
            @JsonProperty("categoryId") int categoryId,
            @JsonProperty("month") Month month,
            @JsonProperty("budgetAmount") double budgetAmount) {
        this.budgetId = budgetId;
        this.categoryId = categoryId;
        this.month = month;
        this.budgetAmount = budgetAmount;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public double getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    @Override
    public String toString() {
        return categoryId + "\t\t" + budgetAmount;
    }
}

