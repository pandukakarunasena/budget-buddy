/**
 * This class is responsible for managing the categories.
 */
package services;

import constants.Constants;
import constants.TransactionType;
import model.Category;

import java.util.ArrayList;
import java.util.List;

// Singleton class
public final class CategoryManager {
    /** Category List. */
    private final List<Category> categoryList;

    /** Last Category ID. Used to calculate the new category ID when adding a new category. */
    private int lastCategoryId;

    /** CategoryManager instance. */
    private static CategoryManager instance;

    /**
     * Constructor for CategoryManager.
     */
    private CategoryManager() {
        this.categoryList = new ArrayList<>();
        this.lastCategoryId = 0;
    }

    /**
     * Get CategoryManager instance.
     *
     * @return CategoryManager instance
     */
    public static CategoryManager getInstance() {
        if (instance == null) {
            instance = new CategoryManager();
        }
        return instance;
    }

    /**
     * Add Category.
     *
     * @param categoryName   Category Name
     * @param transactionType Transaction Type
     */
    public void addCategory(String categoryName, TransactionType transactionType) {
        Category category = new Category(lastCategoryId++, categoryName, transactionType);
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
        throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND + categoryId);
    }

    /**
     * Get Category List.
     *
     * @return Category List
     */
    public List<Category> getCategoryList() {
        return categoryList;
    }

    /**
     * Get Category List from Transaction Type.
     *
     * @param transactionType Transaction Type
     * @return Category List
     */
    public List<Category> getCategoryList(TransactionType transactionType) {
        List<Category> filteredCategoryList = new ArrayList<>();
        for (Category category : categoryList) {
            if (category.getTransactionType() == transactionType) {
                filteredCategoryList.add(category);
            }
        }
        return filteredCategoryList;
    }

    /**
     * Edit Category.
     *
     * @param categoryId     Category ID
     * @param categoryName   Category Name
     * @param transactionType Transaction Type
     */
    public void editCategory(int categoryId, String categoryName, TransactionType transactionType) {
        Category category = getCategory(categoryId);
        category.setCategoryName(categoryName);
        category.setTransactionType(transactionType);
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

