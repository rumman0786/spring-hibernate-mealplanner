package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.utils.HibernateUtil;
import org.hibernate.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author rumman
 * @since 10/26/16
 */
@Repository
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> userList = null;
        try {
            transaction = session.beginTransaction();
            userList = session.createCriteria(User.class).list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList;
    }

    @Override
    public User findById(int userId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        User user = null;
        try {
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, userId);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    @Override
    public List<User> findByName(String username) {
        return null;
    }

    @Override
    public User findByNamePassword(String username, String password) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        User user = null;
        Query query = session.createQuery("from User where username = :username and password =:password");
        query.setParameter("username", username);
        query.setParameter("password", User.makePassword(password));
        List<User> list = query.list();

        if (list.size() > 0) {
            user = list.get(0);
        }
        return user;
    }

    @Override
    public boolean insertUser(User user) {
        if (user == null) {
            return false;
        }
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            session.save(user);
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
    public boolean updateUser(User user) {
        if (user == null) {
            return false;
        }
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            session.update(user);
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
    public boolean deleteUser(User user) {
        if (user == null) {
            return false;
        }
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean status = false;
        try {
            transaction = session.beginTransaction();
            session.delete(user);
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
