package repository;

import dao.UserDao;
import entity.user.NewUser;
import entity.user.User;
import io.vavr.collection.List;

import java.util.Optional;

public class UserRepository {

    private final UserDao<User, NewUser> dao;

    public UserRepository(UserDao<User, NewUser> dao) {
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

    public void update(User user){
        dao.update(user);
    }

    public void enableUser(long id){
        dao.enable(id);
    }

    public boolean existEmail(String email){
        return !getAll().find(user -> user.email.equals(email)).isEmpty();
    }

    public boolean isValidPassword(String email, String password){
        return dao.findByEmail(email).filter(u -> password.equals(u.password)).isPresent();
    }
}
