/**
 * This class is responsible for managing the categories.
 */
package services;

import constants.Constants;
import constants.TransactionType;
import model.Category;

import java.util.ArrayList;
import java.util.List;

public final class CategoryManager {
    /** Category List. */
    private final List<Category> categoryList;

    /** Last Category ID. Used to calculate the new category ID when adding a new category. */
    private int lastCategoryId;

    /**
     * Constructor for CategoryManager.
     */
    public CategoryManager() {

        this.categoryList = new ArrayList<>();
        this.lastCategoryId = 0;
    }

    /**
     * Add Category.
     *
     * @param categoryName   Category Name
     */
    public void addCategory(String categoryName) {
        Category category = new Category(++lastCategoryId, categoryName);
        categoryList.add(category);
    }

    /**
     * Get Category from Category ID.
     *
     * @param categoryId Category ID
     * @return Category
     */
    public Category getCategory(int categoryId) {
        for (Category category : categoryList) {
            if (category.getCategoryId() == categoryId) {
                return category;
            }
        }
        //throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND + categoryId);
        return null;
    }

    public boolean isCategoryExisting(int categoryId) {
        if (this.getCategory(categoryId) != null) {
            return true;
        }
        return false;
    }

    /**
     * Get Category List.
     *
     * @return Category List
     */
    public List<Category> getCategoryList() {
        return categoryList;
    }

    public boolean isCategoryEmpty() {
        if (categoryList != null && !categoryList.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Edit Category.
     *
     * @param categoryId     Category ID
     * @param categoryName   Category Name
     */
    public void editCategory(int categoryId, String categoryName) {
        Category category = getCategory(categoryId);
        category.setCategoryName(categoryName);
    }

    /**
     * Remove Category.
     *
     * @param categoryId Category ID
     */
    public void removeCategory(int categoryId) {
        categoryList.remove(getCategory(categoryId));
        // TODO: Remove budget for this category
        // TODO: Remove transactions for this category?
    }
}

