package org.habitatmclean.dao;

import org.habitatmclean.entity.Zone;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

public class ZoneBO extends GenericDao implements ReadDAO<Zone, Long> {

    @Override
    public Zone findByPrimaryKey(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Object obj = session.load(Zone.class, id);
        return (Zone) obj;
    }

    @Override
    public List<Zone> findAll() {
        Session session = sessionFactory.getCurrentSession();
        Query result = session.createQuery("from Zone");
        return result.list();
    }

}
