package dao;

import entity.user.User;
import io.vavr.collection.List;
import java.util.Optional;

interface UserDao<T, U> extends Dao<T, U> {

    void enable(long id);

    Optional<T> findByEmail(String email);

    List<User> findAllNonRegistered();
}
