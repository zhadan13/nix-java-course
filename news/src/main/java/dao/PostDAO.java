package dao;

import model.Post;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostDAO implements DAO<Post> {
    @Override
    public boolean save(Post post) {
        if (getAll().stream().anyMatch(post1 -> post1.getId().equals(post.getId()))) {
            throw new RuntimeException("ID error!");
        }
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("INSERT INTO news1 VALUES (DEFAULT, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setDate(3, Date.valueOf(post.getDate()));
            preparedStatement.setInt(4, post.getViews());
            preparedStatement.setLong(5, post.getAuthorId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                post.setId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error in save method", e);
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("DELETE FROM news1 WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error in delete method", e);
        }
        return true;
    }

    @Override
    public boolean update(Post post) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("UPDATE news1 SET TITLE = ?, TEXT = ?, DATE = ?, VIEWS = ?, AUTHOR_ID = ? WHERE ID = ?")) {
            preparedStatement.setString(1, post.getTitle());
            preparedStatement.setString(2, post.getText());
            preparedStatement.setDate(3, Date.valueOf(post.getDate()));
            preparedStatement.setInt(4, post.getViews());
            preparedStatement.setLong(5, post.getAuthor().getId());
            preparedStatement.setLong(6, post.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error in update method", e);
        }
        return true;
    }

    @Override
    public Optional<Post> get(Post post) {
        return getById(post.getId());
    }

    @Override
    public Optional<Post> getById(Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM news1 WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return createPost(resultSet);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error in getById method", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Post> getAll() {
        List<Post> posts = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM news1")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Post post = createPost(resultSet).orElse(null);
                posts.add(post);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error in getAll method", e);
        }
        return posts;
    }

    public Optional<User> getPostAuthor(final Long id) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM news1 WHERE ID = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new UserDAO().getById(resultSet.getLong("author_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error in getPostAuthor method", e);
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
        post.setAuthor(new UserDAO().getById(resultSet.getLong("author_id")).orElseThrow(RuntimeException::new));
        return Optional.of(post);
    }
}
