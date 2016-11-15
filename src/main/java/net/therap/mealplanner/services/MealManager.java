package net.therap.mealplanner.services;

import net.therap.mealplanner.dao.MealDao;
import net.therap.mealplanner.dao.MealDaoImpl;
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
    MealDao mealDao;

    @Transactional
    public boolean addMealToMenu(Meal meal) {
//        MealDao mealDao = new MealDaoImpl();
        return mealDao.insertMeal(meal);
    }

    @Transactional
    public boolean updateMealInMenu(Meal meal) {
//        MealDao mealDao = new MealDaoImpl();
        return mealDao.updateMeal(meal);
    }

    @Transactional
    public boolean deleteMealFromMenu(Meal meal) {
//        MealDao mealDao = new MealDaoImpl();
        return mealDao.deleteMeal(meal);
    }

}
