package controllers;

import model.Category;

import java.util.List;

public interface CategoryManager {
    List<Category> getCategoryList();

    boolean isCategoryNotEmpty();

    Category getCategoryById(int categoryId);

    Category getCategoryByName(String categoryName);

    boolean isCategoryExistingById(int categoryId);

    void addCategory(String categoryName);

    void updateCategory(int categoryId, String categoryName);

    void removeCategory(int categoryId);

    String getPrintableCategoryList(String filter);
}
