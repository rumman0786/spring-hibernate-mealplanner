package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.Dish;
import net.therap.mealplanner.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Dish> dishList = null;
        try {
            transaction = session.beginTransaction();
            dishList = session.createCriteria(Dish.class).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dishList;
    }

    @Override
    public Dish findById(int dishId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Dish dish = null;
        try {
            transaction = session.beginTransaction();
            dish = (Dish) session.get(Dish.class, dishId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return dish;
    }

    @Override
    public List<Dish> findByName(String dishName) {
        return null;
    }

//    @Override
//    public boolean insertDish(Dish dish) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
//        Session session = sessionFactory.openSession();
//        Transaction transaction = null;
//        boolean status = false;
//        try {
//            transaction = session.beginTransaction();
//            session.save(dish);
//            transaction.commit();
//            status = true;
//        } catch (HibernateException e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return status;
//    }

    @Override
    public boolean insertDish(Dish dish) {
        entityManager.persist(dish);
        return true;
    }

    @Override
    public boolean deleteDish(Dish dish) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            session.delete(dish);
            transaction.commit();
            status = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }

    @Override
    public boolean updateDish(Dish dish) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            session.update(dish);
            transaction.commit();
            status = true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return status;
    }
}
