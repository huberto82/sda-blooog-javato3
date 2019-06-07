package dao;

import java.util.Set;

public interface TagDao<T,U> extends Dao<T,U> {
    boolean exists(String name);
    void addAll(Set<T> set);

}
