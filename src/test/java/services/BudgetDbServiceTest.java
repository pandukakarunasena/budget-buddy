package services;

import constants.Month;
import model.Budget;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BudgetDbServiceTest {

    @BeforeEach
    void setup() {

        BudgetDbService.getInstance().removeAllBudgets();
    }

    @AfterEach
    void cleanCategoryList() {

        BudgetDbService.getInstance().removeAllBudgets();
    }

    @Test
    @DisplayName("Check class type of the BudgetDbService")
    void getInstanceCheckClassType() {

        BudgetDbService budgetDbService = BudgetDbService.getInstance();
        assertEquals(BudgetDbService.class.getName(), budgetDbService.getClass().getName());
    }

    @Test
    @DisplayName("Check if a single object of BudgetDBService is present")
    void getInstanceCheckSingleton() {

        BudgetDbService budgetDbService1= BudgetDbService.getInstance();
        BudgetDbService budgetDbService2 = BudgetDbService.getInstance();
        assertEquals(budgetDbService1, budgetDbService2);
    }

    @Test
    @DisplayName("Get all Budget when no Budgets added")
    void getAllBudgetsWhenNoBudgets() {

        BudgetDbService budgetDbService = BudgetDbService.getInstance();
        List<Budget> BudgetList = budgetDbService.getAllBudgets();
        assertEquals(0, BudgetList.size());
    }

    @Test
    @DisplayName("Get all Budgets when two Budgets added")
    void getAllBudgetsWhenTwoBudgets() {

        BudgetDbService budgetDbService = BudgetDbService.getInstance();
        budgetDbService.addSingleBudget(new Budget(1,1,Month.April, 5000));
        budgetDbService.addSingleBudget(new Budget(1,1,Month.April, 5000));
        assertEquals(2, budgetDbService.getAllBudgets().size());
    }

    @Test
    @DisplayName("Check empty Budget list")
    void isCategoryEmpty() {

        assertTrue(BudgetDbService.getInstance().isBudgetEmpty());
    }

    @Test
    @DisplayName("Get a Budget by Budget ID")
    void getBudgetById() {

        Budget BudgetToBeAdded = new Budget(1,1,Month.April, 5000);
        BudgetDbService.getInstance().addSingleBudget(BudgetToBeAdded);

        Budget BudgetRetrieved = BudgetDbService.getInstance().getBudgetById(1).get();
        assertEquals(BudgetToBeAdded.getBudgetId(), BudgetRetrieved.getBudgetId());
    }


    @Test
    void getBudgetsByCategoryId() {

        Budget BudgetToBeAdded = new Budget(1,1,Month.April, 5000);
        BudgetDbService.getInstance().addSingleBudget(BudgetToBeAdded);

        List<Budget> BudgetsRetrieved = BudgetDbService.getInstance().getBudgetsByCategoryId(1);

        assertEquals(1,BudgetsRetrieved.size());
    }

    @Test
    void getBudgetsByMonth() {

        Budget BudgetToBeAdded = new Budget(1,1,Month.April, 5000);
        BudgetDbService.getInstance().addSingleBudget(BudgetToBeAdded);

        List<Budget> BudgetsRetrieved = BudgetDbService.getInstance().getBudgetsByMonth(Month.April);

        assertEquals(1,BudgetsRetrieved.size());
    }

    @Test
    void addAllBudgets() {

        List<Budget> BudgetToBeAddedList = new ArrayList<>();
        BudgetToBeAddedList.add(new Budget(1,1,Month.April, 5000));
        BudgetToBeAddedList.add(new Budget(1,1,Month.April, 5000));

        BudgetDbService.getInstance().addAllBudgets(BudgetToBeAddedList);
        List<Budget> BudgetListRetrieved = BudgetDbService.getInstance().getAllBudgets();
        assertEquals(BudgetToBeAddedList.size(), BudgetListRetrieved.size());
    }

    @Test
    void addSingleBudget() {

        Budget budgetToBeAdded = new Budget(1,1,Month.April, 5000);
        BudgetDbService.getInstance().addSingleBudget(budgetToBeAdded);

        Budget budgetRetrieved = BudgetDbService.getInstance().getBudgetById(1).get();

        assertEquals(budgetRetrieved.getBudgetId(), budgetToBeAdded.getBudgetId());
    }

    @Test
    void updateBudget() {

        Budget BudgetToBeAdded = new Budget(1,1,Month.April, 5000);
        BudgetDbService.getInstance().addSingleBudget(BudgetToBeAdded);

        Budget BudgetToBeUpdated = new Budget(1,1,Month.April, 5000);

        BudgetDbService.getInstance().updateBudget(BudgetToBeUpdated);

        Budget BudgetRetrieved = BudgetDbService.getInstance().getBudgetById(1).get();

        assertEquals(BudgetToBeUpdated.getBudgetId(), BudgetRetrieved.getBudgetId());
    }

    @Test
    void removeBudgetByBudgetId() {

        Budget BudgetToBeAdded = new Budget(1,1,Month.April, 5000);
        BudgetDbService.getInstance().addSingleBudget(BudgetToBeAdded);

        BudgetDbService.getInstance().removeBudgetByBudgetId(1);
        assertTrue(BudgetDbService.getInstance().getAllBudgets().isEmpty());
    }

    @Test
    void removeBudgetsByCategoryId() {

        Budget BudgetToBeAdded = new Budget(1,1,Month.April, 5000);
        BudgetDbService.getInstance().addSingleBudget(BudgetToBeAdded);

        BudgetDbService.getInstance().removeBudgetByCategoryId(1);
        assertTrue(BudgetDbService.getInstance().getAllBudgets().isEmpty());
    }
}
