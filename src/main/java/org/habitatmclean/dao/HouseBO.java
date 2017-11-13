package org.habitatmclean.dao;

import org.habitatmclean.entity.House;

public class HouseBO extends GenericDao<House> {
    public HouseBO() {
        super(House.class);
    }
}
