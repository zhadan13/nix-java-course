package dao;

import models.User;

import java.util.Set;

public interface UserDao {
    User getUserById(Long id);

    boolean deleteUserById(Long id);

    boolean insertUser(User user);

    boolean updateUser(User user);

    Set<User> getAllUsers();

    User getUserByUserNameAndPassword(String name, String password);
}