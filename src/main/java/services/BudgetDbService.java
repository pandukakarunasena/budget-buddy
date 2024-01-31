package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import constants.Month;
import model.Budget;
import model.Transaction;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BudgetDbService {
    private final String folderPath = "src/main/java/db/";
    private final String budgetFilePath = "budget.json";
    private final ObjectMapper objectMapper;
    private volatile static BudgetDbService instance;

    public static BudgetDbService getInstance() {
        if (instance == null) {
            synchronized (BudgetDbService.class) {
                if (instance == null) {
                    instance = new BudgetDbService();
                }
            }
        }
        return instance;
    }

    private void initializeDataFile() {
        File file = new File(folderPath, budgetFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private BudgetDbService() {
        initializeDataFile();
        this.objectMapper = new ObjectMapper();
    }

    public List<Budget> getAllBudgets() {
        try {
            File file = new File(folderPath, budgetFilePath);

            if (!file.exists() || file.length() == 0) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<Budget>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean isBudgetEmpty() {
        List<Budget> budgets = getAllBudgets();
        return budgets == null || budgets.isEmpty();
    }

    public Optional<Budget> getBudgetById(int budgetId) {
        List<Budget> budgets = getAllBudgets();
        return budgets.stream()
                .filter(budget -> budget.getBudgetId() == budgetId)
                .findFirst();
    }

    public List<Budget> getBudgetsByCategoryId(int categoryId) {
        List<Budget> allBudgets = getAllBudgets();
        return allBudgets.stream()
                .filter(budget -> budget.getCategoryId() == categoryId)
                .collect(Collectors.toList());
    }

    public List<Budget> getBudgetsByMonth(Month month) {
        List<Budget> allBudgets = getAllBudgets();
        return allBudgets.stream()
                .filter(budget -> budget.getMonth().equals(month))
                .collect(Collectors.toList());
    }

    public List<Budget> getBudgetsByCategoryIdAndMonth(int categoryId, Month month) {
        List<Budget> allBudgets = getAllBudgets();
        return allBudgets.stream()
                .filter(budget -> budget.getCategoryId() == categoryId && budget.getMonth() == month)
                .collect(Collectors.toList());
    }

    public List<Budget> getBudgetsByAmount(double amount) {
        List<Budget> allBudgets = getAllBudgets();
        return allBudgets.stream()
                .filter(budget -> budget.getBudgetAmount() == amount)
                .collect(Collectors.toList());
    }

    public synchronized void addAllBudgets(List<Budget> budgets) {
        try {
            objectMapper.writeValue(new File(folderPath, budgetFilePath), budgets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void addSingleBudget(Budget budget) {
        List<Budget> budgets = getAllBudgets();
        budgets.add(budget);
        addAllBudgets(budgets);
    }

    public synchronized void updateBudget(Budget updatedBudget) {
        List<Budget> budgets = getAllBudgets();
        for (int i = 0; i < budgets.size(); i++) {
            Budget budget = budgets.get(i);
            if (budget.getBudgetId() == updatedBudget.getBudgetId()) {
                budgets.set(i, updatedBudget);
                addAllBudgets(budgets);
                return;
            }
        }
    }

    public synchronized void removeBudgetByBudgetId(int budgetId) {
        List<Budget> budgets = getAllBudgets();
        budgets.removeIf(budget -> budget.getBudgetId() == budgetId);
        addAllBudgets(budgets);
    }

    public synchronized void removeBudgetByCategoryId(int categoryId) {
        List<Budget> budgets = getAllBudgets();
        budgets.removeIf(budget -> budget.getCategoryId() == categoryId);
        addAllBudgets(budgets);
    }

    public synchronized void removeAllBudgets() {
        List<Budget> budgets = new ArrayList<>();
        addAllBudgets(budgets);
    }
}
