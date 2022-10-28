package com.revature.daos;

import java.util.List;

public interface DatabaseCRUD<T> {
    void create(T t);
    void create(T t, int id);
    T read(int id);
    T read(int id,int id1);
    List<T> readAll();
    void update(T t);
    void delete(int id);
    List<T> readAll(int id);
}
