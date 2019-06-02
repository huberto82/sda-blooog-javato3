package repository;

import dao.TagDao;
import entity.tag.TagEntity;
import io.vavr.collection.List;

public class TagRepository {
    private TagDao<TagEntity, TagEntity> dao;

    public TagRepository(TagDao<TagEntity, TagEntity> dao) {
        this.dao = dao;
    }

    public List<TagEntity> getAll(){
        return dao.getAll();
    }

    public long add(TagEntity te){
        return dao.save(te);
    }

    public boolean exist(String name){
        return dao.exists(name);
    }
}
