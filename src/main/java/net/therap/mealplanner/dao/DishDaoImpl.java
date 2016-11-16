package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.Dish;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
    @SuppressWarnings("unchecked")
    public List<Dish> findAll() {
        return (List<Dish>) entityManager.createQuery("from Dish").getResultList();
    }

    @Override
    @Transactional
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
        Dish attachedDish = entityManager.getReference(Dish.class, dish.getId());
        entityManager.remove(attachedDish);
        return true;
    }

    @Override
    public boolean updateDish(Dish dish) {
        entityManager.merge(dish);
        return true;
    }
}
