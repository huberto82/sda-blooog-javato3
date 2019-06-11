package mapper;

import entity.tag.Tag;
import entity.tag.TagEntity;

public enum TagMapper implements Mapper<TagEntity, Tag> {

    INSTANCE;

    @Override
    public TagEntity toEntity(Tag obj) {
        return new TagEntity(obj);
    }

    @Override
    public Tag toDomain(TagEntity obj) {
        return new Tag(obj);
    }
}
