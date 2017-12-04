package org.habitatmclean.dao;

import java.io.Serializable;
import java.util.SortedSet;

public interface ReadDAO<T, ID extends Serializable> {
    T findByPrimaryKey(ID id);
    SortedSet<T> findAll();
}
