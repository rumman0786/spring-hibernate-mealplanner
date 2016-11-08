package net.therap.mealplanner.services;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.dao.DishDaoImpl;
import net.therap.mealplanner.entity.Dish;
import org.springframework.stereotype.Service;

/**
 * @author rumman
 * @since 10/17/16
 */
@Service
public class DishManager {

    public boolean addDish(Dish dish) {
        DishDao dishDao = new DishDaoImpl();
        return dishDao.insertDish(dish);
    }

    public boolean updateDish(Dish dish) {
        DishDao dishDao = new DishDaoImpl();
        return dishDao.updateDish(dish);
    }

    public boolean deleteDish(Dish dish) {
        DishDao dishDao = new DishDaoImpl();
        return dishDao.deleteDish(dish);
    }

}
