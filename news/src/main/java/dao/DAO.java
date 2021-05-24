package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;

public interface DAO<T> {
    boolean save(T t);

    boolean delete(Long id);

    boolean update(T t);

    Optional<T> get(T t);

    Optional<T> getById(Long id);

    Collection<T> getAll();

    default Connection getConnection() {
        final String URL = "jdbc:postgresql://localhost:5432/news";
        final String USER = "postgres";
        final String PASSWORD = "12345678";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}
