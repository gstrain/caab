package org.habitatmclean.dao;

import java.io.Serializable;

public interface CreateUpdateDeleteDAO<T, ID extends Serializable> {
    T save(T entity);
    void delete(T entity);
}
