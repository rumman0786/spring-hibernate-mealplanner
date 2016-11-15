package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.Dish;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rumman
 * @since 10/16/16
 */
@Repository
public class DishDaoImpl implements DishDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Dish> findAll() {
        return (List<Dish>) entityManager.createQuery("from Dish").getResultList();
    }

    @Override
    public Dish findById(int dishId) {
        return entityManager.find(Dish.class, dishId);
    }

    @Override
    public List<Dish> findByName(String dishName) {
        return null;
    }

    @Override
    public boolean insertDish(Dish dish) {
        entityManager.persist(dish);
        return true;
    }

    @Override
    public boolean deleteDish(Dish dish) {
        entityManager.remove(entityManager.contains(dish) ? dish : entityManager.merge(dish));
        return true;
    }

    @Override
    public boolean updateDish(Dish dish) {
        entityManager.merge(dish);
        return true;
    }
}
