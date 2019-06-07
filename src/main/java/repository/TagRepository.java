package repository;

import dao.TagDao;
import entity.tag.NewTag;
import entity.tag.Tag;
import entity.tag.TagEntity;
import io.vavr.collection.List;

public class TagRepository {
    private TagDao<Tag, NewTag> dao;

    public TagRepository(TagDao<Tag, NewTag> dao) {
        this.dao = dao;
    }

    public List<Tag> getAll(){
        return dao.getAll();
    }

    public long add(NewTag te){
        return dao.save(te);
    }

    public boolean exist(String name){
        return dao.exists(name);
    }
}
