package org.habitatmclean.hibernate;

import org.habitatmclean.dao.*;
import org.habitatmclean.entity.*;
import org.hibernate.SessionFactory;

import java.lang.Class;


public class HibernateAdapter extends GenericDao {
    private static final ActorBO ACTOR_BO = new ActorBO();
    private static final AddressBO ADDRESS_BO = new AddressBO();
    private static final ClassBO CLASS_BO = new ClassBO();
    private static final ConstructionStatusBO CONSTRUCTION_STATUS_BO = new ConstructionStatusBO();
    private static final FamilyBO FAMILY_BO = new FamilyBO();
    private static final HouseContributionBO HOUSE_CONTRIBUTION_BO = new HouseContributionBO();
    private static final HouseBO HOUSE_BO = new HouseBO();
    private static final HouseStyleBO HOUSE_STYLE_BO = new HouseStyleBO();
    private static final LogBO LOG_BO = new LogBO();
    private static final MilestoneBO MILESTONE_BO = new MilestoneBO();
    private static final OrganizationBO ORGANIZATION_BO = new OrganizationBO();
    private static final PersonBO PERSON_BO = new PersonBO();
    private static final PropertyBO PROPERTY_BO = new PropertyBO();
    private static final PropertyStatusBO PROPERTY_STATUS_BO = new PropertyStatusBO();
    private static final RelationTypeBO RELATION_TYPE_BO = new RelationTypeBO();
    private static final ZoneBO ZONE_BO = new ZoneBO();

    @Override
    public GenericEntity save(GenericEntity entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CreateUpdateDeleteDAO dao = new GenericDao();
        sessionFactory.getCurrentSession().beginTransaction();
        dao.save(entity);
        sessionFactory.getCurrentSession().getTransaction().commit();
        return entity;
    }
    @Override
    public void delete(GenericEntity entity) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        CreateUpdateDeleteDAO dao = new GenericDao();
        sessionFactory.getCurrentSession().beginTransaction();
        dao.delete(entity);
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    /**
     *
     * @param className the case-sensitive class name of an object
     * @return a DAO that implements ReadDAO, null if className doesn't extend GenericEntity
     */
    public static GenericDao getBoByEntityName(String className) {
        switch(className) {
//            case "Actor":
//                return ACTOR_BO;
            case "Address":
                return ADDRESS_BO;
            case "Class":
                return CLASS_BO;
            case "ConstructionStatus":
                return CONSTRUCTION_STATUS_BO;
            case "Family":
                return FAMILY_BO;
            case "House":
                return HOUSE_BO;
            case "HouseContribution":
                return HOUSE_CONTRIBUTION_BO;
            case "HouseStyle":
                return HOUSE_STYLE_BO;
            case "Log":
                return LOG_BO;
            case "Milestone":
                return MILESTONE_BO;
            case "Organization":
                return ORGANIZATION_BO;
            case "Person":
                return PERSON_BO;
            case "Property":
                return PROPERTY_BO;
            case "PropertyStatus":
                return PROPERTY_STATUS_BO;
            case "RelationType":
                return RELATION_TYPE_BO;
            case "Zone":
                return ZONE_BO;
        }
        return null;

    }
    private GenericDao getBoByEntity(GenericEntity entity) {
        String objName = entity.getClass().getCanonicalName();
        if (objName.contains(".")) {
            objName = objName.substring(objName.lastIndexOf(".")+1);
        }
        return getBoByEntityName(objName);
    }

    public static GenericDao getBoByEntityName(Class<?> clazz) {
        if(clazz == Actor.class) {
            return ACTOR_BO;
        } else if (clazz == Address.class){
            return ADDRESS_BO;
        } else if (clazz == Class.class){
            return CLASS_BO;
        } else if (clazz == ConstructionStatus.class){
            return CONSTRUCTION_STATUS_BO;
        } else if (clazz == Family.class){
            return FAMILY_BO;
        } else if (clazz == House.class){
            return HOUSE_BO;
        } else if (clazz == HouseContribution.class){
            return HOUSE_CONTRIBUTION_BO;
        } else if (clazz == Log.class){
            return LOG_BO;
        } else if (clazz == Milestone.class){
            return MILESTONE_BO;
        } else if (clazz == Organization.class){
            return ORGANIZATION_BO;
        } else if (clazz == Person.class){
            return PERSON_BO;
        } else if (clazz == Property.class){
            return PROPERTY_BO;
        } else if (clazz == RelationType.class){
            return RELATION_TYPE_BO;
        } else if (clazz == Zone.class){
            return ZONE_BO;
        }


        else
            return null;
    }
}
