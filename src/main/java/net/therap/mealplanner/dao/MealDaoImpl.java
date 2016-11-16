package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.utils.HibernateUtil;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author rumman
 * @since 10/16/16
 */
@Repository
public class MealDaoImpl implements MealDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<Meal> findAll() {
        return (List<Meal>) entityManager.createQuery("from Meal").getResultList();
    }

    @Override
    public Meal findById(int mealId) {
        return entityManager.find(Meal.class, mealId);
    }

    @Override
    public List<Meal> findByName(String mealName) {
        throw new UnsupportedOperationException("Feature not implemented");
    }

    @Override
    public boolean insertMeal(Meal meal) {
        Set<Dish> newDishSet = new HashSet<Dish>();

        for (Dish dish: meal.getDishSet()){
            newDishSet.add(entityManager.getReference(Dish.class, dish.getId()));
        }

        meal.setDishSet(newDishSet);
        entityManager.persist(meal);

        return true;
    }

    @Override
    public boolean updateMeal(Meal meal) {
        entityManager.merge(meal);

        return true;
    }

    @Override
    public boolean deleteMeal(Meal meal) {
        Meal attachedMeal = entityManager.getReference(Meal.class, meal.getId());
        entityManager.remove(attachedMeal );

        return true;
    }
}
