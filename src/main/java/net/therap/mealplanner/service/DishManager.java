package net.therap.mealplanner.service;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.entity.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author rumman
 * @since 10/17/16
 */
@Service
public class DishManager {

    @Autowired
    private DishDao dishDao;

    @Transactional
    public boolean addDish(Dish dish) {
        return dishDao.insertDish(dish);
    }

    @Transactional
    public boolean updateDish(Dish dish) {
        return dishDao.updateDish(dish);
    }

    @Transactional
    public boolean deleteDish(Dish dish) {
        return dishDao.deleteDish(dish);
    }

}
