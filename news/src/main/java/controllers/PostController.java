package controllers;

import connection.ConnectionFactory;
import dao.Dao;
import models.Post;
import models.User;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class PostController implements Dao<Post> {
    @Override
    public boolean save(Post post) {
        if (getAll().stream().anyMatch(post1 -> post1.getId().equals(post.getId()))) {
            throw new RuntimeException("ID error!");
        }
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "INSERT INTO NEWS VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setLong(1, post.getId());
            preparedStatement.setString(2, post.getTitle());
            preparedStatement.setString(3, post.getText());
            preparedStatement.setDate(4, Date.valueOf(post.getDate()));
            preparedStatement.setInt(5, post.getViews());
            preparedStatement.setLong(6, post.getAuthor().getId());

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
            String sqlString = "DELETE FROM NEWS WHERE ID = ?";
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
    public boolean update(Post post) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "UPDATE NEWS SET TITLE = ?, TEXT = ?, DATE = ?, VIEWS = ?, AUTHOR_ID = ? WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setDate(3, Date.valueOf(post.getDate()));
            preparedStatement.setInt(4, post.getViews());
            preparedStatement.setLong(5, post.getAuthor().getId());
            preparedStatement.setLong(6, post.getId());

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
    public Optional<Post> get(Post post) {
        return getById(post.getId());
    }

    @Override
    public Optional<Post> getById(Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "SELECT * FROM NEWS WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createPost(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public List<Post> getAll() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "SELECT * FROM NEWS";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            ResultSet resultSet = preparedStatement.executeQuery();

            List<Post> posts = new ArrayList<>();
            while (resultSet.next()) {
                Post post = createPost(resultSet).orElse(null);
                posts.add(post);
            }

            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Optional<User> getPostAuthor(final Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "SELECT * FROM NEWS WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new UserController().getById(resultSet.getLong("author_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private static Optional<Post> createPost(final ResultSet resultSet) throws SQLException {
        Post post = new Post();
        post.setId(resultSet.getLong("id"));
        post.setTitle(resultSet.getString("title"));
        post.setText(resultSet.getString("text"));
        post.setDate(resultSet.getDate("date").toLocalDate());
        post.setViews(resultSet.getInt("views"));
        post.setAuthor(new UserController().getById(resultSet.getLong("author_id")).orElse(null));
        return Optional.of(post);
    }
}