package repository;

import dao.Dao;
import entity.NewUser;
import entity.User;
import io.vavr.collection.List;

import java.util.Optional;

public class UserRepository {

    private final Dao<User, NewUser> dao;

    public UserRepository(Dao<User, NewUser> dao) {
        this.dao = dao;
    }

    public Optional<User> get(long id){
        return dao.get(id);
    }

    public void add(NewUser newUser){
        dao.save(newUser);
    }

    public List<User> getAll(){
        return dao.getAll();
    }

    public boolean existEmail(String email){
        return !getAll().find(user -> user.email.equals(email)).isEmpty();
    }
}
