package dao;
import entity.tag.NewTag;
import entity.tag.Tag;
import entity.tag.TagEntity;
import io.vavr.collection.List;
import io.vavr.collection.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;
import java.util.stream.Collectors;

class TagDaoJPA implements TagDao<Tag, NewTag>{

    private final EntityManager em;

    public TagDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<Tag> getAll() {
        em.getTransaction().begin();
        Query q = em.createQuery("From TagEntity", TagEntity.class);
        List<Tag> result = List.ofAll(q.getResultStream().map(ae -> new Tag((TagEntity) ae)));
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Optional<Tag> get(long id) {
        return Optional.empty();
    }

    @Override
    public long save(NewTag obj) {
        em.getTransaction().begin();
        TagEntity te = new TagEntity(obj);
        em.persist(te);
        em.getTransaction().commit();
        return te.getId();
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void update(Tag obj) {

    }

    @Override
    public boolean exists(String name) {
        return Optional.of(em.createQuery("Select t from TagEntity where name =:name")
                .setParameter("name", name)
                .getSingleResult()
        ).isPresent();
    }

    @Override
    public void addAll(java.util.Set<Tag> set) {
        for(Tag te: set){
            em.getTransaction().begin();
            em.persist(te);
            em.getTransaction().commit();
        }
    }
}
