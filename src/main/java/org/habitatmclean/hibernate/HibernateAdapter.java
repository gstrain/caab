package org.habitatmclean.hibernate;

import org.habitatmclean.dao.*;
import org.habitatmclean.entity.GenericEntity;
import org.hibernate.SessionFactory;


public class HibernateAdapter implements CreateUpdateDeleteDAO<GenericEntity, Long> {
    private static final ActorBO ACTOR_DAO = new ActorBO();
    private static final AddressBO ADDRESS_DAO= new AddressBO();
    private static final ClassBO CLASS_DAO = new ClassBO();
    private static final ConstructionStatusBO CONSTRUCTION_STATUS_DAO = new ConstructionStatusBO();
    private static final FamilyBO FAMILY_DAO = new FamilyBO();
    private static final HouseContributionBO HOUSE_CONTRIBUTION_DAO = new HouseContributionBO();
    private static final HouseBO HOUSE_DAO = new HouseBO();
    private static final HouseStyleBO HOUSE_STYLE_DAO = new HouseStyleBO();
    private static final LogBO LOG_DAO = new LogBO();
    private static final MilestoneBO MILESTONE_DAO = new MilestoneBO();
    private static final OrganizationBO ORGANIZATION_DAO = new OrganizationBO();
    private static final PersonBO PERSON_DAO = new PersonBO();
    private static final PropertyBO PROPERTY_DAO = new PropertyBO();
    private static final PropertyStatusBO PROPERTY_STATUS_DAO = new PropertyStatusBO();
    private static final RelationTypeBO RELATION_TYPE_DAO = new RelationTypeBO();
    private static final ZoneBO ZONE_DAO = new ZoneBO();

    @Override
    public GenericEntity save(GenericEntity entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CreateUpdateDeleteDAO dao = getBoByEntity(entity);
        sessionFactory.getCurrentSession().beginTransaction();
        dao.save(entity);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return entity;
    }

    @Override
    public void delete(GenericEntity entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CreateUpdateDeleteDAO dao = getBoByEntity(entity);
        sessionFactory.getCurrentSession().beginTransaction();
        dao.delete(entity);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    /**
     *
     * @param className the case-sensitive class name of the object
     * @return the DAO that implements ReadDAO or null if not found
     */
    public static ReadDAO getBoByEntityName(String className) {
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
    private CreateUpdateDeleteDAO getBoByEntity(GenericEntity entity) {
        String objName = entity.getClass().getCanonicalName();
        if (objName.contains(".")) {
            objName = objName.substring(objName.lastIndexOf(".")+1);
        }
        if(objName.equals("Zone")) return null; // Zone does not implement CreateUpdateDeleteDAO as of 11/2/17
        return (CreateUpdateDeleteDAO) getBoByEntityName(objName);

    }

}
