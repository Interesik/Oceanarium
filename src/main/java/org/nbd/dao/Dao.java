package org.nbd.dao;

public interface Dao<T> {

    void create(T obj);
    
    T read(long id);

    void update(Runnable updateFunction);

    void delete(T obj);
}
