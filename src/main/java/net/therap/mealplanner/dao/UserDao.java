package net.therap.mealplanner.dao;

import net.therap.mealplanner.entity.User;

import java.util.List;

/**
 * @author rumman
 * @since 10/26/16
 */
public interface UserDao {

    List<User> findAll();

    User findById(int userId);

    List<User> findByName(String username);

    User findByNamePassword(String username, String password);

    boolean insertUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(User user);

}
