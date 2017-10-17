package org.habitatmclean.dao;

import java.io.Serializable;

public interface ReadDAO<T, ID extends Serializable> {
    T findByPrimaryKey(ID id);
}
