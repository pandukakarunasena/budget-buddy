package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Category;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDbService {
    private final String folderPath = "src/main/java/db/";
    private final String categoryFilePath = "category.json";
    private final ObjectMapper objectMapper;
    private volatile static CategoryDbService instance;

    public static CategoryDbService getInstance() {
        if (instance == null) {
            synchronized (CategoryDbService.class) {
                if (instance == null) {
                    instance = new CategoryDbService();
                }
            }
        }
        return instance;
    }

    private void initializeDataFile() {
        File file = new File(folderPath, categoryFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public CategoryDbService() {
        initializeDataFile();
        this.objectMapper = new ObjectMapper();
    }

    public List<Category> getAllCategories() {
        try {
            File file = new File(folderPath, categoryFilePath);

            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Category>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean isCategoryEmpty() {
        List<Category> categories = getAllCategories();
        return categories == null || categories.isEmpty();
    }

    public Optional<Category> getCategoryById(int categoryId) {
        List<Category> categories = getAllCategories();
        return categories.stream()
                .filter(category -> category.getCategoryId() == categoryId)
                .findFirst();
    }

    public Optional<Integer> getCategoryIdByName(String categoryName) {
        List<Category> categories = getAllCategories();
        for (Category category : categories) {
            if (category.getCategoryName().equals(categoryName)) {
                return Optional.of(category.getCategoryId());
            }
        }
        return Optional.empty();
    }

    public synchronized void addAllCategories(List<Category> categories) {
        try {
            objectMapper.writeValue(new File(folderPath, categoryFilePath), categories);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addSingleCategory(Category category) {
        List<Category> categories = getAllCategories();
        categories.add(category);
        addAllCategories(categories);
    }

    public synchronized void updateCategory(Category updatedCategory) {
        List<Category> categories = getAllCategories();
        for (int i = 0; i < categories.size(); i++) {
            Category category = categories.get(i);
            if (category.getCategoryId() == updatedCategory.getCategoryId()) {
                categories.set(i, updatedCategory);
                addAllCategories(categories);
                return;
            }
        }
    }

    public synchronized void removeCategoryById(int categoryId) {
        List<Category> categories = getAllCategories();
        categories.removeIf(category -> category.getCategoryId() == categoryId);
        addAllCategories(categories);
    }
}
