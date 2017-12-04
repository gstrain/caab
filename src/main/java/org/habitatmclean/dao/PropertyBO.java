package org.habitatmclean.dao;

import org.habitatmclean.entity.GenericEntity;
import org.habitatmclean.entity.Log;
import org.habitatmclean.entity.Property;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class PropertyBO<T extends GenericEntity> extends GenericDao<Property> {
    public PropertyBO() {
        super(Property.class);
    }
}
