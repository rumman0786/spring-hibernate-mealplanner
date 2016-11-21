package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rumman
 * @since 10/26/16
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    @Override
    public User findById(int userId) {
        return entityManager.find(User.class, userId);
    }

    @Override
    public List<User> findByName(String username) {
        throw new UnsupportedOperationException("Feature not implemented");
    }

    @Override
    @SuppressWarnings("unchecked")
    public User findByNamePassword(String username, String password) {
        User user = null;
        List<User> list = (List<User>) entityManager.createQuery("Select u from User u where u.username = :userName and u.password =:passWord")
                .setParameter("userName", username)
                .setParameter("passWord", User.makePassword(password))
                .getResultList();

        if (list.size() > 0) {
            user = list.get(0);
        }

        return user;
    }

    @Override
    @Transactional
    public boolean insertUser(User user) {
        entityManager.persist(user);

        return true;
    }

    @Override
    @Transactional
    public boolean updateUser(User user) {
        entityManager.merge(user);

        return true;
    }

    @Override
    @Transactional
    public boolean deleteUser(User user) {
        User attachedUser = entityManager.getReference(User.class, user.getId());
        entityManager.remove(attachedUser);

        return true;
    }
}
