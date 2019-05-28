package dao;

import entity.user.NewUser;
import entity.user.User;
import entity.user.UserEntity;
import io.vavr.collection.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

public class UserDaoJPA implements UserDao<User, NewUser> {

    private final EntityManager em;

    public UserDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<User> getAll() {
        em.getTransaction().begin();
        Query q = em.createQuery("From UserEntity");
        List<User> result = List.ofAll(q.getResultStream().map(e -> new User((UserEntity) e)));
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Optional<User> get(long id) {
        return getEntity(id).map(user -> new User(user));
    }

    @Override
    public void save(NewUser obj) {
        em.getTransaction().begin();
        em.persist(new UserEntity(obj));
        em.getTransaction().commit();
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(User obj) {

    }

    private Optional<UserEntity> getEntity(long id) {
        try {
            return Optional.of(em
                    .createQuery("Select u from UserEntity u where id = :id", UserEntity.class)
                    .setParameter("id", id)
                    .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public void enable(long id) {
        em.getTransaction().begin();
        getEntity(id).map(user -> {
            user.setEnabled(true);
            return user;
        }).ifPresent(em::persist);
        em.getTransaction().commit();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            return Optional.of(em
                    .createQuery("Select u from UserEntity u where email like :email", UserEntity.class)
                    .setParameter("email", email)
                    .getSingleResult()).map(ue -> new User(ue));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAllNonRegistered() {
        return List.ofAll(em
                .createQuery("Select u from UserEntity u where enabled = true", UserEntity.class)
                .getResultStream()
                .map(ue -> new User(ue)));
    }
}
