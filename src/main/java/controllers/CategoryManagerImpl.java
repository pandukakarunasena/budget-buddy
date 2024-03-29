/**
 * This class is responsible for managing the categories.
 */
package controllers;

import constants.Constants;
import model.Category;
import services.CategoryDbService;
import services.CategoryDbServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class CategoryManagerImpl implements CategoryManager {
    private final CategoryDbService categoryDbService;
    private final AtomicInteger lastCategoryId;

    public CategoryManagerImpl() {
        this.categoryDbService = CategoryDbServiceImpl.getInstance();
        this.lastCategoryId = new AtomicInteger(getMaxCategoryId());
    }

    private int getMaxCategoryId() {
        List<Category> categoryList = categoryDbService.getAllCategories();
        return categoryList.stream()
                .mapToInt(Category::getCategoryId)
                .max()
                .orElse(0);
    }

    @Override
    public List<Category> getCategoryList() {
        List<Category> categoryList = categoryDbService.getAllCategories();
        if (categoryList != null && !categoryList.isEmpty()) {
            return categoryList;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_CATEGORY);
        }
    }

    @Override
    public boolean isCategoryNotEmpty() {
        if (!categoryDbService.isCategoryEmpty()) {
            return true;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_CATEGORY);
        }
    }

    @Override
    public Category getCategoryById(int categoryId) {
        Optional<Category> optionalCategory = categoryDbService.getCategoryById(categoryId);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_ID + categoryId);
        }
    }

    @Override
    public Category getCategoryByName(String categoryName) {
        Optional<Integer> categoryIdOptional = categoryDbService.getCategoryIdByName(categoryName);
        if (categoryIdOptional.isPresent()) {
            Category category = new Category(categoryIdOptional.get(), categoryName);
            return category;
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_NAME + categoryName);
        }
    }

    @Override
    public boolean isCategoryExistingById(int categoryId) {
        try {
            Category returnedCategory = getCategoryById(categoryId);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public void addCategory(String categoryName) {
        Optional<Integer> categoryIdOptional = categoryDbService.getCategoryIdByName(categoryName);
        if (!categoryIdOptional.isPresent()) {
            int newCategoryId = lastCategoryId.incrementAndGet();
            Category category = new Category(newCategoryId, categoryName);
            categoryDbService.addSingleCategory(category);
        } else {
            int categoryId = categoryIdOptional.get();
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_FOUND_BY_NAME + categoryName);
        }
    }

    @Override
    public void updateCategory(int categoryId, String categoryName) {
        if (this.isCategoryNotEmpty()) {
            Category category = getCategoryById(categoryId);
            if (category != null) {
                category.setCategoryName(categoryName);
                categoryDbService.updateCategory(category);
            } else {
                throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_ID + categoryId);
            }
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_CATEGORY);
        }
    }

    @Override
    public void removeCategory(int categoryId) {
        if (isCategoryExistingById(categoryId)) {
            categoryDbService.removeCategoryById(categoryId);
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_CATEGORY_NOT_FOUND_BY_ID + categoryId);
        }
    }

    @Override
    public String getPrintableCategoryList(String filter) {
        List<Category> categoryList;
        if (filter.equals("all")) {
            categoryList = categoryDbService.getAllCategories();
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_PRINTABLE_CATEGORY_LIST_DOES_NOT_EXIST);
        }

        if (categoryList != null && !categoryList.isEmpty()) {
            StringBuilder result = new StringBuilder();
            result.append("Category ID\tCategory Name\n");

            for (Category category : categoryList) {
                result.append(category.getCategoryId())
                        .append("\t\t")
                        .append(category.getCategoryName())
                        .append("\n");
            }

            return result.toString();
        } else {
            throw new IllegalArgumentException(Constants.ERROR_MESSAGE_EMPTY_CATEGORY);
        }
    }
}

