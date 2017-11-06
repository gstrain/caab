package org.habitatmclean.hibernate;

import org.habitatmclean.dao.*;
import org.habitatmclean.entity.GenericEntity;
import org.hibernate.SessionFactory;


public class HibernateAdapter implements CreateUpdateDeleteDAO<GenericEntity, Long> {
    private static final ActorDAO ACTOR_DAO = new ActorDAO(HibernateUtil.getSessionFactory());
    private static final AddressDAO ADDRESS_DAO= new AddressDAO(HibernateUtil.getSessionFactory());
    private static final ClassDAO CLASS_DAO = new ClassDAO(HibernateUtil.getSessionFactory());
    private static final ConstructionStatusDAO CONSTRUCTION_STATUS_DAO = new ConstructionStatusDAO(HibernateUtil.getSessionFactory());
    private static final FamilyDAO FAMILY_DAO = new FamilyDAO(HibernateUtil.getSessionFactory());
    private static final HouseContributionDAO HOUSE_CONTRIBUTION_DAO = new HouseContributionDAO(HibernateUtil.getSessionFactory());
    private static final HouseDAO HOUSE_DAO = new HouseDAO(HibernateUtil.getSessionFactory());
    private static final HouseStyleDAO HOUSE_STYLE_DAO = new HouseStyleDAO(HibernateUtil.getSessionFactory());
    private static final LogDAO LOG_DAO = new LogDAO(HibernateUtil.getSessionFactory());
    private static final MilestoneDAO MILESTONE_DAO = new MilestoneDAO(HibernateUtil.getSessionFactory());
    private static final OrganizationDAO ORGANIZATION_DAO = new OrganizationDAO(HibernateUtil.getSessionFactory());
    private static final PersonDAO PERSON_DAO = new PersonDAO(HibernateUtil.getSessionFactory());
    private static final PropertyDAO PROPERTY_DAO = new PropertyDAO(HibernateUtil.getSessionFactory());
    private static final PropertyStatusDAO PROPERTY_STATUS_DAO = new PropertyStatusDAO(HibernateUtil.getSessionFactory());
    private static final RelationTypeDAO RELATION_TYPE_DAO = new RelationTypeDAO(HibernateUtil.getSessionFactory());
    private static final ZoneDAO ZONE_DAO = new ZoneDAO(HibernateUtil.getSessionFactory());

    @Override
    public GenericEntity save(GenericEntity entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CreateUpdateDeleteDAO dao = getDaoByEntity(entity);
        sessionFactory.getCurrentSession().beginTransaction();
        dao.save(entity);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(GenericEntity entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CreateUpdateDeleteDAO dao = getDaoByEntity(entity);
        sessionFactory.getCurrentSession().beginTransaction();
        dao.delete(entity);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    /**
     *
     * @param className the case-sensitive class name of the object
     * @return the DAO that implements ReadDAO or null if not found
     */
    public static ReadDAO getDaoByEntityName(String className) {
        switch(className) {
            case "Actor":
                return ACTOR_DAO;
            case "Address":
                return ADDRESS_DAO;
            case "Class":
                return CLASS_DAO;
            case "ConstructionStatus":
                return CONSTRUCTION_STATUS_DAO;
            case "Family":
                return FAMILY_DAO;
            case "House":
                return HOUSE_DAO;
            case "HouseContribution":
                return HOUSE_CONTRIBUTION_DAO;
            case "HouseStyle":
                return HOUSE_STYLE_DAO;
            case "Log":
                return LOG_DAO;
            case "Milestone":
                return MILESTONE_DAO;
            case "Organization":
                return ORGANIZATION_DAO;
            case "Person":
                return PERSON_DAO;
            case "Property":
                return PROPERTY_DAO;
            case "PropertyStatus":
                return PROPERTY_STATUS_DAO;
            case "RelationType":
                return RELATION_TYPE_DAO;
            case "Zone":
                return ZONE_DAO;
        }
        return null;

    }
    private CreateUpdateDeleteDAO getDaoByEntity(GenericEntity entity) {
        String objName = entity.getClass().getCanonicalName();
        if (objName.contains(".")) {
            objName = objName.substring(objName.lastIndexOf(".")+1);
        }
        if(objName.equals("Zone")) return null; // Zone does not implement CreateUpdateDeleteDAO as of 11/2/17
        return (CreateUpdateDeleteDAO) getDaoByEntityName(objName);

    }

}
