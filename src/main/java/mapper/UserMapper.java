package mapper;

import entity.user.User;
import entity.user.UserEntity;

public enum UserMapper implements  Mapper<UserEntity, User>{

    INSTANCE;

    @Override
    public UserEntity toEntity(User obj) {
        return new UserEntity(obj);
    }

    @Override
    public User toDomain(UserEntity obj) {
        return new User(obj);
    }
}
