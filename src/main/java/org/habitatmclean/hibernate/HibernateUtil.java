package org.habitatmclean.hibernate;
import org.habitatmclean.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.lang.Class;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration()
                    .configure()
                    .addPackage("org.habitatmclean.entity")
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Class.class)
                    .addAnnotatedClass(ConstructionStatus.class)
                    .addAnnotatedClass(Family.class)
                    .addAnnotatedClass(House.class)
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
}