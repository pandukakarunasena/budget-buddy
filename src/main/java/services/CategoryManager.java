package services;

import constants.TransactionType;
import model.Category;

import java.util.ArrayList;
import java.util.List;

// Singleton class
public class CategoryManager {
    private final List<Category> categoryList;
    private int lastCategoryId;

    private static CategoryManager instance;

    private CategoryManager() {
        this.categoryList = new ArrayList<>();
        this.lastCategoryId = 0;
    }

    public static CategoryManager getInstance() {
        if (instance == null) {
            instance = new CategoryManager();
        }
        return instance;
    }

    // Add Category
    public void addCategory(String categoryName, TransactionType transactionType) {
        Category category = new Category(lastCategoryId++, categoryName, transactionType);
        categoryList.add(category);
    }

    // Get Category from Category ID
    public Category getCategory(int categoryId) {
        for (Category category : categoryList) {
            if (category.getCategoryId() == categoryId) {
                return category;
            }
        }
        throw new IllegalArgumentException("Category not found for category ID: " + categoryId);
    }

    // Get Category List
    public List<Category> getCategoryList() {
        return categoryList;
    }

    // Get Category Type
    public List<Category> getCategoryList(TransactionType transactionType) {
        List<Category> filteredCategoryList = new ArrayList<>();
        for (Category category : categoryList) {
            if (category.getTransactionType() == transactionType) {
                filteredCategoryList.add(category);
            }
        }
        return filteredCategoryList;
    }

    // Edit Category
    public void editCategory(int categoryId, String categoryName, TransactionType transactionType) {
        Category category = getCategory(categoryId);
        category.setCategoryName(categoryName);
        category.setTransactionType(transactionType);
    }

    // Remove Category
    public void removeCategory(int categoryId) {
        categoryList.remove(getCategory(categoryId));
    }
}

