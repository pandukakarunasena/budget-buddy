/**
 * Model class for Budget.
 */
package model;

public class Budget {

    /** Category ID. */
    private final int categoryId;

    /** Budget Amount. */
    private double budgetAmount;

    /**
     * Constructor for Budget.
     *
     * @param categoryId   Category ID
     * @param budgetAmount Budget Amount
     */
    public Budget(int categoryId, double budgetAmount) {
        this.categoryId = categoryId;
        this.budgetAmount = budgetAmount;
    }

    /**
     * Get Category ID.
     *
     * @return Category ID
     */
    public int getCategoryId() {
        return categoryId;
    }

    /**
     * Get Budget Amount.
     *
     * @return Budget Amount
     */
    public double getBudgetAmount() {
        return budgetAmount;
    }

    /**
     * Set Budget Amount.
     *
     * @param budgetAmount Budget Amount
     */
    public void setBudgetAmount(double budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    /**
     * Override toString() method.
     *
     * @return String
     */
    @Override
    public String toString() {
        return categoryId + "\t\t" + budgetAmount;
    }
}

