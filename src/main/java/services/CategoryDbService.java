package services;

import model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryDbService {

    List<Category> getAllCategories();

    boolean isCategoryEmpty();

    Optional<Category> getCategoryById(int categoryId);

    Optional<Integer> getCategoryIdByName(String categoryName);

    void addAllCategories(List<Category> categories);

    void addSingleCategory(Category category);

    void updateCategory(Category updatedCategory);

    void removeCategoryById(int categoryId);

    void removeAllCategories();
}
