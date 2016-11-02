package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.Meal;
import net.therap.mealplanner.utils.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        Transaction tx = session.beginTransaction();
        List<Meal> mealList = session.createCriteria(Meal.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        tx.commit();
        session.close();
        return mealList;
    }

    @Override
    public Meal findById(int mealId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Meal meal = (Meal) session.get(Meal.class, mealId);
        session.close();
        return meal;
    }

    @Override
    public List<Meal> findByName(String mealName) {
        return null;
    }

    @Override
    public boolean insertMeal(Meal meal) {
        List<Meal> mealList = findAll();
        if (!mealList.contains(meal)) {
            SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(meal);
            tx.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateMeal(Meal meal) {
        List<Meal> mealList = findAll();
//        if (!mealList.contains(meal)) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(meal);
        transaction.commit();
        session.close();
        return true;
//        }
//        return false;
    }

    @Override
    public boolean deleteMeal(Meal meal) {
        List<Meal> mealList = findAll();
        if (mealList.contains(meal)) {
            SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.delete(meal);
            tx.commit();
            session.close();
            return true;
        }
        return false;
    }

}
