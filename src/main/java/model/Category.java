/**
 * Model class for Category.
 */
package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import constants.TransactionType;

public class Category {
    /** Category ID. */
    private final int categoryId;

    /** Category Name. */
    private String categoryName;

    /**
     * Constructor for Category.
     *
     * @param categoryId     Category ID
     * @param categoryName   Category Name
     */

    @JsonCreator
    public Category(
            @JsonProperty("categoryId") int categoryId,
            @JsonProperty("categoryName") String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
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
     * Get Category Name.
     *
     * @return Category Name
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * Set Category Name.
     *
     * @param categoryName Category Name
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * Override toString() method.
     *
     * @return String
     */
    @Override
    public String toString() {
        return categoryId + "\t\t" + categoryName;
    }
}

