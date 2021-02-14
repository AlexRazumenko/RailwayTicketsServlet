package ua.alex.railway.tickets.dao;

import java.util.List;

public interface GenericDAO<T> extends AutoCloseable {

    void create(T entity);

    T findById(long id);

    List<T> findAll();

    void update(T entity);

    void delete(long id);

    void close();


}
