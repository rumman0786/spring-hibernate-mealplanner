package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.Meal;

import java.util.List;

/**
 * @author rumman
 * @since 10/16/16
 */
public interface MealDao {

    List<Meal> findAll();

    Meal findById(int mealId);

    List<Meal> findByName(String mealName);

    boolean insertMeal(Meal meal);

    boolean updateMeal(Meal meal);

    boolean deleteMeal(Meal meal);

}