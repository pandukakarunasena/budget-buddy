package services;

import constants.TransactionType;
import model.Category;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryManagerTest {

    private static CategoryManager categoryManager;

    @BeforeAll
    public static void setup() {

        categoryManager = new CategoryManager();
    }

    @BeforeEach
    public void addACategory() {

        categoryManager.addCategory("Food");
    }

    @AfterEach
    public void clearCategories() {

        categoryManager.getCategoryList().clear();
    }

    @Test
    @DisplayName("Add Category Food")
    public void addCategory() {

        List<Category> categoryList = categoryManager.getCategoryList();
        assertEquals(1, categoryList.size());
    }

    @Test
    @DisplayName("Get Category Food")
    public void getCategory() {

//        Category category = categoryManager.getCategory(0);
//        assertEquals("Food", category.getCategoryName());
    }

    @Test
    @DisplayName("Get category list and assert size")
    public void getCategoryList() {

        categoryManager.addCategory("Salary");
        List<Category> categoryList = categoryManager.getCategoryList();
        assertEquals(2, categoryList.size());
    }

    @Test
    @DisplayName("Edit Category Food with Salary")
    public void editCategory() {

        categoryManager.editCategory(1,"Salary");

        Category category = categoryManager.getCategory(1);
        assertEquals("Salary",category.getCategoryName());
    }

    @Test
    @DisplayName("Remove Category Food")
    public void removeCategory() {

        List<Category> categoryList =  categoryManager.getCategoryList();
        categoryManager.removeCategory(categoryList.size());
        assertEquals(1, categoryManager.getCategoryList().size());
    }
}