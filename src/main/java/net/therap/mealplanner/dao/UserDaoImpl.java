package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.User;
import net.therap.mealplanner.utils.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * @author rumman
 * @since 10/26/16
 */
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll() {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        List<User> userList = session.createCriteria(User.class).list();
        tx.commit();
        session.close();
        return userList;
    }

    @Override
    public User findById(int userId) {
        SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, userId);
        session.close();
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
        List<User> dishList = findAll();
        if (!dishList.contains(user)) {
            SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        if (user != null) {
            SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        if (user != null) {
            SessionFactory sessionFactory = HibernateUtil.getSessionAnnotationFactory();
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
            session.close();
            return true;
        }
        return false;
    }
}
