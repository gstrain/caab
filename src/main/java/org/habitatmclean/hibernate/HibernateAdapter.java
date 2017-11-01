package org.habitatmclean.hibernate;

import org.habitatmclean.dao.*;
import org.hibernate.SessionFactory;


public class HibernateAdapter implements CreateUpdateDeleteDAO {

    @Override
    public Object save(Object entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CreateUpdateDeleteDAO dao = getDaoByEntity(entity, sessionFactory);
        sessionFactory.getCurrentSession().beginTransaction();
        dao.save(entity);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(Object entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CreateUpdateDeleteDAO dao = getDaoByEntity(entity, sessionFactory);
        sessionFactory.getCurrentSession().beginTransaction();
        dao.delete(entity);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

//    @Override
//    public Object findByPrimaryKey(Serializable serializable) {
//        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//        ReadDAO dao = (ReadDAO) getDaoByEntity(, sessionFactory);
//    }

//    @Override
//    public List findAll() {
//        return null;
//    }

    private CreateUpdateDeleteDAO getDaoByEntity(Object entity, SessionFactory sessionFactory) {
        String objName = entity.getClass().getCanonicalName();
        if (objName.indexOf(".") != -1) {
            objName = objName.substring(objName.lastIndexOf(".")+1);
        }
        switch(objName) {
            case "Actor":
                return new ActorDAO(sessionFactory);
            case "Address":
                return new AddressDAO(sessionFactory);
            case "Class":
                return new ClassDAO(sessionFactory);
            case "ConstructionStatus":
                return new ConstructionStatusDAO(sessionFactory);
            case "Family":
                return new FamilyDAO(sessionFactory);
            case "House":
                return new HouseDAO(sessionFactory);
            case "HouseContribution":
                return new HouseContributionDAO(sessionFactory);
            case "HouseStyle":
                return new HouseStyleDAO(sessionFactory);
            case "Log":
                return new LogDAO(sessionFactory);
            case "Milestone":
                return new MilestoneDAO(sessionFactory);
            case "Organization":
                return new OrganizationDAO(sessionFactory);
            case "Person":
                return new PersonDAO(sessionFactory);
            case "Property":
                return new PropertyDAO(sessionFactory);
            case "PropertyStatus":
                return new PropertyStatusDAO(sessionFactory);
            case "RelationType":
                return new RelationTypeDAO(sessionFactory);
//            case "Zone":                      // Zone currently does not implement CreateUpdateDeleteDAO because the specifications at this time did not have zones being editable
//                return new ZoneDAO(sessionFactory);
        }
        return null;
    }

//    private ReadDAO getDaoByEntity(String type, SessionFactory sessionFactory) {
//
//        return null;
//    }

}
