package com.knight.hibernate_demo.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static final String CONFIG_FILE = "/hibernate.cfg.xml";
    private static Configuration config = new Configuration();
    private static SessionFactory sessionFactory;
    private static ThreadLocal<Session> threadLocal = new ThreadLocal<>();


    static {
        try {
            config.configure(CONFIG_FILE);
            sessionFactory = config.buildSessionFactory();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private HibernateSessionFactory() {

    }


    public static Session getSession() {
        Session session = threadLocal.get();
        if (session == null || !session.isOpen()) {
            session = sessionFactory != null ? sessionFactory.openSession() : null;
            threadLocal.set(session);
        }
        return session;
    }

    public static void closeSession() {
        Session session = threadLocal.get();
        if (session != null) {
            threadLocal.set(null);
            session.close();
        }
    }

}
