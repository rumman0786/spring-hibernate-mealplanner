package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.utils.HibernateUtil;
import org.hibernate.*;

import java.util.List;

/**
 * @author rumman
 * @since 10/16/16
 */
public class MealDaoImpl implements MealDao {

    @Override
    public List<Meal> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Meal> mealList = null;
        try {
            transaction = session.beginTransaction();
            mealList = session.createCriteria(Meal.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return mealList;
    }

    @Override
    public Meal findById(int mealId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Meal meal = null;
        try {
            transaction = session.beginTransaction();
            meal = (Meal) session.get(Meal.class, mealId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return meal;
    }

    @Override
    public List<Meal> findByName(String mealName) {
        return null;
    }

    @Override
    public boolean insertMeal(Meal meal) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        boolean status = false;
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(meal);
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
    public boolean updateMeal(Meal meal) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            session.merge(meal);
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
    public boolean deleteMeal(Meal meal) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            session.delete(meal);
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
