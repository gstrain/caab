package org.habitatmclean.hibernate;

import org.habitatmclean.dao.*;
import org.hibernate.SessionFactory;


public class HibernateAdapter implements CreateUpdateDeleteDAO {
    private static ActorDAO actorDAO = new ActorDAO(HibernateUtil.getSessionFactory());
    private static AddressDAO addressDAO = new AddressDAO(HibernateUtil.getSessionFactory());
    private static ClassDAO classDAO = new ClassDAO(HibernateUtil.getSessionFactory());
    private static ConstructionStatusDAO constructionStatusDAO = new ConstructionStatusDAO(HibernateUtil.getSessionFactory());
    private static FamilyDAO familyDAO = new FamilyDAO(HibernateUtil.getSessionFactory());
    private static HouseContributionDAO houseContributionDAO = new HouseContributionDAO(HibernateUtil.getSessionFactory());
    private static HouseDAO houseDAO = new HouseDAO(HibernateUtil.getSessionFactory());
    private static HouseStyleDAO houseStyleDAO = new HouseStyleDAO(HibernateUtil.getSessionFactory());
    private static LogDAO logDAO = new LogDAO(HibernateUtil.getSessionFactory());
    private static MilestoneDAO milestoneDAO = new MilestoneDAO(HibernateUtil.getSessionFactory());
    private static OrganizationDAO organizationDAO = new OrganizationDAO(HibernateUtil.getSessionFactory());
    private static PersonDAO personDAO = new PersonDAO(HibernateUtil.getSessionFactory());
    private static PropertyDAO propertyDAO = new PropertyDAO(HibernateUtil.getSessionFactory());
    private static PropertyStatusDAO propertyStatusDAO = new PropertyStatusDAO(HibernateUtil.getSessionFactory());
    private static RelationTypeDAO relationTypeDAO = new RelationTypeDAO(HibernateUtil.getSessionFactory());
    private static ZoneDAO zoneDAO = new ZoneDAO(HibernateUtil.getSessionFactory());

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

    private CreateUpdateDeleteDAO getDaoByEntity(Object entity, SessionFactory sessionFactory) {
        String objName = entity.getClass().getCanonicalName();
        if (objName.contains(".")) {
            objName = objName.substring(objName.lastIndexOf(".")+1);
        }
        switch(objName) {
            case "Actor":
                return actorDAO;
            case "Address":
                return addressDAO;
            case "Class":
                return classDAO;
            case "ConstructionStatus":
                return constructionStatusDAO;
            case "Family":
                return familyDAO;
            case "House":
                return houseDAO;
            case "HouseContribution":
                return houseContributionDAO;
            case "HouseStyle":
                return houseStyleDAO;
            case "Log":
                return logDAO;
            case "Milestone":
                return milestoneDAO;
            case "Organization":
                return organizationDAO;
            case "Person":
                return personDAO;
            case "Property":
                return propertyDAO;
            case "PropertyStatus":
                return propertyStatusDAO;
            case "RelationType":
                return relationTypeDAO;
//            case "Zone":                      // Zone currently does not implement CreateUpdateDeleteDAO because the specifications at this time did not have zones being editable
//                return new ZoneDAO(sessionFactory);
        }
        return null;
    }

}
