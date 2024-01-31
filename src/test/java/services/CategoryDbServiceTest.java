package services;

import model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDbServiceTest {


    @BeforeEach
    void setup() {

        CategoryDbService.getInstance().removeAllCategories();
    }

    @AfterEach
    void cleanCategoryList() {

        CategoryDbService.getInstance().removeAllCategories();
    }

    @Test
    @DisplayName("Check class type of the CategoryDbService")
    void getInstanceCheckClassType() {

        CategoryDbService categoryDbService = CategoryDbService.getInstance();
        assertEquals(CategoryDbService.class.getName(), categoryDbService.getClass().getName());
    }

    @Test
    @DisplayName("Check if a single object of CategoryDBService is present")
    void getInstanceCheckSingleton() {

        CategoryDbService categoryDbService1 = CategoryDbService.getInstance();
        CategoryDbService categoryDbService2 = CategoryDbService.getInstance();
        assertEquals(categoryDbService1, categoryDbService2);
    }

    @Test
    @DisplayName("Get all categories when no categories added")
    void getAllCategoriesWhenNoCategories() {

        CategoryDbService categoryDbService = CategoryDbService.getInstance();
        List<Category> categoryList = categoryDbService.getAllCategories();
        assertEquals(0, categoryList.size());
    }

    @Test
    @DisplayName("Get all categories when two categories added")
    void getAllCategoriesWhenTwoCategories() {

        CategoryDbService categoryDbService = CategoryDbService.getInstance();
        categoryDbService.addSingleCategory(new Category(1,"Food"));
        categoryDbService.addSingleCategory(new Category(2,"Salary"));
        List<Category> categoryList = categoryDbService.getAllCategories();
        assertEquals(2, categoryList.size());
    }

    @Test
    @DisplayName("Check empty category list")
    void isCategoryEmpty() {

        assertTrue(CategoryDbService.getInstance().isCategoryEmpty());
    }

    @Test
    @DisplayName("Get Category By ID when no category available")
    void getCategoryById() {

        Optional<Category> category = CategoryDbService.getInstance().getCategoryById(1);
        assertFalse(category.isPresent());
    }

    @Test
    @DisplayName("Get Category By ID when category is available")
    void getCategoryIdByName() {

        CategoryDbService.getInstance().addSingleCategory(new Category(1, "Salary"));
        Optional<Category> category = CategoryDbService.getInstance().getCategoryById(1);
        assertTrue(category.isPresent());
        assertEquals(1,category.get().getCategoryId());
    }

    @Test
    void addAllCategories() {

        List<Category> categoryList = new ArrayList<>();

        categoryList.add(new Category(1, "Food"));
        categoryList.add(new Category(2, "Salary"));

        CategoryDbService.getInstance().addAllCategories(categoryList);
        assertEquals(categoryList.size(), CategoryDbService.getInstance().getAllCategories().size());
    }

    @Test
    void addSingleCategory() {

        CategoryDbService.getInstance().addSingleCategory(new Category(1,"Food"));
        assertEquals("Food", CategoryDbService.getInstance().getCategoryById(1).get().getCategoryName());
    }

    @Test
    void updateCategory() {

        CategoryDbService.getInstance().addSingleCategory(new Category(1,"Food"));
        CategoryDbService.getInstance().updateCategory(new Category(1, "Salary"));
        assertEquals("Salary", CategoryDbService.getInstance().getCategoryById(1).get().getCategoryName());
    }

    @Test
    void removeCategoryById() {

        CategoryDbService.getInstance().addSingleCategory(new Category(1,"Food"));
        CategoryDbService.getInstance().removeCategoryById(1);
        assertEquals(0, CategoryDbService.getInstance().getAllCategories().size());
    }
}