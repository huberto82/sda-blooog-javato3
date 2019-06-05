package repository;

import dao.TagDao;
import entity.tag.NewTag;
import entity.tag.TagEntity;
import io.vavr.collection.List;

public class TagRepository {
    private TagDao<TagEntity, NewTag> dao;

    public TagRepository(TagDao<TagEntity, NewTag> dao) {
        this.dao = dao;
    }

    public List<TagEntity> getAll(){
        return dao.getAll();
    }

    public long add(NewTag te){
        return dao.save(te);
    }

    public boolean exist(String name){
        return dao.exists(name);
    }
}
