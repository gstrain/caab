package org.habitatmclean.dao;

import java.io.Serializable;
import java.util.List;

public interface CreateUpdateDeleteDAO<T, ID extends Serializable> {
    T save(T entity);
    void delete(T entity);
    List<T> findAll();
}
