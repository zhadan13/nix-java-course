package dao;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {

    boolean save(T t);

    boolean delete(Long id);

    boolean update(T t);

    Optional<T> get(T t);

    Optional<T> getById(Long id);

    Collection<T> getAll();
}