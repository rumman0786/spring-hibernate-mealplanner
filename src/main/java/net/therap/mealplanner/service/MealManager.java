package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.MealDao;
import net.therap.mealplanner.entity.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rumman
 * @since 10/16/16
 */
@Service
public class MealManager {

    @Autowired
    private MealDao mealDao;

    @Transactional
    public boolean addMealToMenu(Meal meal) {
        return mealDao.insertMeal(meal);
    }

    @Transactional
    public boolean updateMealInMenu(Meal meal) {
        return mealDao.updateMeal(meal);
    }

    @Transactional
    public boolean deleteMealFromMenu(Meal meal) {
        return mealDao.deleteMeal(meal);
    }
}
