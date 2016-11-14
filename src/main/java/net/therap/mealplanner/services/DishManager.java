package net.therap.mealplanner.services;

import net.therap.mealplanner.dao.DishDao;
import net.therap.mealplanner.dao.DishDaoImpl;
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
//        DishDao dishDao = new DishDaoImpl();
        return dishDao.insertDish(dish);
    }

    @Transactional
    public boolean updateDish(Dish dish) {
//        DishDao dishDao = new DishDaoImpl();
        return dishDao.updateDish(dish);
    }

    @Transactional
    public boolean deleteDish(Dish dish) {
//        DishDao dishDao = new DishDaoImpl();
        return dishDao.deleteDish(dish);
    }

}
