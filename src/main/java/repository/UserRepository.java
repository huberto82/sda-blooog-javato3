package repository;

import dao.UserDao;
import entity.user.NewUser;
import entity.user.User;
import entity.user.UserEntity;
import io.vavr.collection.List;

import java.time.LocalDateTime;
import java.util.Optional;

public class UserRepository {

    private final UserDao<User, NewUser> dao;

    public UserRepository(UserDao<User, NewUser> dao) {
        this.dao = dao;
    }

    public Optional<User> get(long id){
        return dao.get(id);
    }

    public long add(NewUser newUser){
        dao.save(newUser);
        return 0;
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

    public Optional<User> findByEmail(String email){
        return dao.findByEmail(email);
    }

    public Optional<User> login(String email, String password){
        Optional<User> logedUser =  findByEmail(email).map(user -> user.password.equals(password) && user.enabled ? user : null);
        return logedUser.map(user -> {
            UserEntity ue = user.toUserEntity();
            ue.setLastLogin(LocalDateTime.now());
            User u = ue.toUser();
            dao.update(u);
            return u;
        });
    }
}
