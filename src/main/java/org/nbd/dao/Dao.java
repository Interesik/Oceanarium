package org.nbd.dao;

public interface Dao<T> {

    long create(T obj);

    T read(long id);

    void update(Runnable updateFunction);

    void delete(T obj);
}
