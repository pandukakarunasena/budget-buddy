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

class CategoryDbServiceImplTest {


    @BeforeEach
    void setup() {

        CategoryDbServiceImpl.getInstance().removeAllCategories();
    }

    @AfterEach
    void cleanCategoryList() {

        CategoryDbServiceImpl.getInstance().removeAllCategories();
    }

    @Test
    @DisplayName("Check class type of the CategoryDbServiceImpl")
    void getInstanceCheckClassType() {

        CategoryDbService categoryDbService = CategoryDbServiceImpl.getInstance();
        assertEquals(CategoryDbServiceImpl.class.getName(), categoryDbService.getClass().getName());
    }

    @Test
    @DisplayName("Check if a single object of CategoryDBService is present")
    void getInstanceCheckSingleton() {

        CategoryDbService categoryDbService1 = CategoryDbServiceImpl.getInstance();
        CategoryDbService categoryDbService2 = CategoryDbServiceImpl.getInstance();
        assertEquals(categoryDbService1, categoryDbService2);
    }

    @Test
    @DisplayName("Get all categories when no categories added")
    void getAllCategoriesWhenNoCategories() {

        CategoryDbService categoryDbService = CategoryDbServiceImpl.getInstance();
        List<Category> categoryList = categoryDbService.getAllCategories();
        assertEquals(0, categoryList.size());
    }

    @Test
    @DisplayName("Get all categories when two categories added")
    void getAllCategoriesWhenTwoCategories() {

        CategoryDbService categoryDbService = CategoryDbServiceImpl.getInstance();
        categoryDbService.addSingleCategory(new Category(1,"Food"));
        categoryDbService.addSingleCategory(new Category(2,"Salary"));
        List<Category> categoryList = categoryDbService.getAllCategories();
        assertEquals(2, categoryList.size());
    }

    @Test
    @DisplayName("Check empty category list")
    void isCategoryEmpty() {

        assertTrue(CategoryDbServiceImpl.getInstance().isCategoryEmpty());
    }

    @Test
    @DisplayName("Get Category By ID when no category available")
    void getCategoryById() {

        Optional<Category> category = CategoryDbServiceImpl.getInstance().getCategoryById(1);
        assertFalse(category.isPresent());
    }

    @Test
    @DisplayName("Get Category By ID when category is available")
    void getCategoryIdByName() {

        CategoryDbServiceImpl.getInstance().addSingleCategory(new Category(1, "Salary"));
        Optional<Category> category = CategoryDbServiceImpl.getInstance().getCategoryById(1);
        assertTrue(category.isPresent());
        assertEquals(1,category.get().getCategoryId());
    }

    @Test
    void addAllCategories() {

        List<Category> categoryList = new ArrayList<>();

        categoryList.add(new Category(1, "Food"));
        categoryList.add(new Category(2, "Salary"));

        CategoryDbServiceImpl.getInstance().addAllCategories(categoryList);
        assertEquals(categoryList.size(), CategoryDbServiceImpl.getInstance().getAllCategories().size());
    }

    @Test
    void addSingleCategory() {

        CategoryDbServiceImpl.getInstance().addSingleCategory(new Category(1,"Food"));
        assertEquals("Food", CategoryDbServiceImpl.getInstance().getCategoryById(1).get().getCategoryName());
    }

    @Test
    void updateCategory() {

        CategoryDbServiceImpl.getInstance().addSingleCategory(new Category(1,"Food"));
        CategoryDbServiceImpl.getInstance().updateCategory(new Category(1, "Salary"));
        assertEquals("Salary", CategoryDbServiceImpl.getInstance().getCategoryById(1).get().getCategoryName());
    }

    @Test
    void removeCategoryById() {

        CategoryDbServiceImpl.getInstance().addSingleCategory(new Category(1,"Food"));
        CategoryDbServiceImpl.getInstance().removeCategoryById(1);
        assertEquals(0, CategoryDbServiceImpl.getInstance().getAllCategories().size());
    }
}