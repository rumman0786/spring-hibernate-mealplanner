package net.therap.mealplanner.services;

import net.therap.mealplanner.dao.MealDao;
import net.therap.mealplanner.dao.MealDaoImpl;
import net.therap.mealplanner.entity.Meal;
import org.springframework.stereotype.Service;

/**
 * @author rumman
 * @since 10/16/16
 */
@Service
public class MealManager {

    public boolean addMealToMenu(Meal meal) {
        MealDao mealDao = new MealDaoImpl();
        return mealDao.insertMeal(meal);
    }

    public boolean updateMealInMenu(Meal meal) {
        MealDao mealDao = new MealDaoImpl();
        return mealDao.updateMeal(meal);
    }

    public boolean deleteMealFromMenu(Meal meal) {
        MealDao mealDao = new MealDaoImpl();
        return mealDao.deleteMeal(meal);
    }

}
