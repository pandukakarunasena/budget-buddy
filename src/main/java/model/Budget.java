package model;

public class Budget {
    private final int categoryId;
    private double budgetAmount;

    public Budget(int categoryId, double budgetAmount) {
        this.categoryId = categoryId;
        this.budgetAmount = budgetAmount;
    }

    public int getCategoryId() {
        return categoryId;
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

