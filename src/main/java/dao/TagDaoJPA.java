package dao;
import entity.tag.NewTag;
import entity.tag.TagEntity;
import io.vavr.collection.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;
import java.util.Set;

class TagDaoJPA implements TagDao<TagEntity, NewTag>{

    private final EntityManager em;

    public TagDaoJPA(EntityManager em) {
        this.em = em;
    }

    @Override
    public List<TagEntity> getAll() {
        em.getTransaction().begin();
        Query q = em.createQuery("From TagEntity");
        List<TagEntity> result = List.ofAll(q.getResultList());
        em.getTransaction().commit();
        return result;
    }

    @Override
    public Optional<TagEntity> get(long id) {
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
    public void update(TagEntity obj) {

    }

    @Override
    public boolean exists(String name) {
        return Optional.of(em.createQuery("Select t from TagEntity where name =:name")
                .setParameter("name", name)
                .getSingleResult()
        ).isPresent();
    }

    @Override
    public void addAll(Set<TagEntity> set) {
        for(TagEntity te: set){
            em.getTransaction().begin();
            em.persist(te);
            em.getTransaction().commit();
        }
    }
}
