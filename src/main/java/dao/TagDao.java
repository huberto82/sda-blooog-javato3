package dao;

import javax.persistence.Persistence;
import java.util.Set;

interface TagDao<T,U> extends Dao<T,U> {
    boolean exists(String name);

    void addAll(Set<T> set);
}
