package org.habitatmclean.dao;

import java.io.Serializable;
import java.util.List;

public interface ReadDAO<T, ID extends Serializable> {
    T findByPrimaryKey(ID id);
    List<T> findAll();
}
