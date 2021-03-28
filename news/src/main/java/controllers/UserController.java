package controllers;

import connection.ConnectionFactory;
import dao.Dao;
import models.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class UserController implements Dao<User> {
    @Override
    public boolean save(User user) {
        if (getAll().stream().anyMatch(user1 -> user1.getId().equals(user.getId()))) {
            throw new RuntimeException("ID error!");
        }
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getAge());
            preparedStatement.setString(5, user.getEmail());

            int executeUpdateResult = preparedStatement.executeUpdate();

            if (executeUpdateResult == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "DELETE FROM USER WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setLong(1, id);

            int executeUpdateResult = preparedStatement.executeUpdate();

            if (executeUpdateResult == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean update(User user) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "UPDATE USER SET NAME = ?, PASSWORD = ?, AGE = ?, EMAIL = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setInt(3, user.getAge());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setLong(5, user.getId());

            int executeUpdateResult = preparedStatement.executeUpdate();

            if (executeUpdateResult == 1) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public Optional<User> get(User user) {
        return getById(user.getId());
    }

    @Override
    public Optional<User> getById(Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "SELECT * FROM USER WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(createUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Set<User> getAll() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "SELECT * FROM USER";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            ResultSet resultSet = preparedStatement.executeQuery();

            Set<User> users = new HashSet<>();
            while (resultSet.next()) {
                User user = createUser(resultSet);
                users.add(user);
            }

            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static User createUser(final ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        user.setAge(resultSet.getInt("age"));
        user.setEmail(resultSet.getString("email"));
        return user;
    }
}