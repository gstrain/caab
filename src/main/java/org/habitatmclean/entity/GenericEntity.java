package org.habitatmclean.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class GenericEntity implements Comparable<GenericEntity> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public abstract String getValueByPropertyName(String property);

    @Override
    public int compareTo(GenericEntity other) {
        return (id > other.getId()) ? 1 : -1;
    }
}
