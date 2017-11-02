package org.habitatmclean.hibernate;
import org.habitatmclean.entity.*;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.proxy.HibernateProxy;

import java.lang.Class;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .addPackage("org.habitatmclean.entity")
                    .addAnnotatedClass(Actor.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Class.class)
                    .addAnnotatedClass(ConstructionStatus.class)
                    .addAnnotatedClass(Family.class)
                    .addAnnotatedClass(House.class)
                    .addAnnotatedClass(HouseContribution.class)
                    .addAnnotatedClass(HouseStyle.class)
                    .addAnnotatedClass(Log.class)
                    .addAnnotatedClass(Milestone.class)
                    .addAnnotatedClass(Organization.class)
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Property.class)
                    .addAnnotatedClass(PropertyStatus.class)
                    .addAnnotatedClass(RelationType.class)
                    .addAnnotatedClass(Zone.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session openSession() {
        return sessionFactory.openSession();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /*
 * turns objects returned by a hibernate query into real objects. those returned by a
 * lazy fetch query are technically of type HibernateProxy. Useful if we want to serialize something
 * returned by a query, but it MUST be used DURING the session (see above example)
 */
    public static <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new
                    NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }
}