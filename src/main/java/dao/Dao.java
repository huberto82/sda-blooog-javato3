package dao;

import io.vavr.collection.List;

import java.util.Optional;

public interface Dao<T, U> {
    List<T> getAll();

    Optional<T> get(long id);

    void save(U obj);

    void delete(long id);

    void update(T obj);
}
