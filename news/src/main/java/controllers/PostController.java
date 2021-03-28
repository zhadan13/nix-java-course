package controllers;

import connection.ConnectionFactory;
import dao.PostDao;
import models.Post;
import models.User;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class PostController implements PostDao {
    public Post getPostById(final Long id) {
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

        return null;
    }

    public Post getPostByTitle(final String title) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "SELECT * FROM NEWS WHERE TITLE = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createPost(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean deletePostById(final Long id) {
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

    public boolean insertPost(final Post post) {
        if (getAllPosts().stream().anyMatch(post1 -> post1.getId().equals(post.getId()))) {
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

    public boolean updatePost(final Post post) {
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

    public Set<Post> getAllPosts() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "SELECT * FROM NEWS";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            ResultSet resultSet = preparedStatement.executeQuery();

            Set<Post> posts = new HashSet<>();
            while (resultSet.next()) {
                Post post = createPost(resultSet);
                posts.add(post);
            }

            return posts;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User getPostAuthor(final Long id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sqlString = "SELECT * FROM NEWS WHERE ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlString);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new UserController().getUserById(resultSet.getLong("author_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Post createPost(final ResultSet resultSet) throws SQLException {
        Post post = new Post();
        post.setId(resultSet.getLong("id"));
        post.setTitle(resultSet.getString("title"));
        post.setText(resultSet.getString("text"));
        post.setDate(resultSet.getDate("date").toLocalDate());
        post.setViews(resultSet.getInt("views"));
        post.setAuthor(new UserController().getUserById(resultSet.getLong("author_id")));
        return post;
    }
}