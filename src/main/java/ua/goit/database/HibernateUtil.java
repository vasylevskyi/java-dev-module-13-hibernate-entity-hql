package ua.goit.database;

import lombok.Data;
import lombok.Getter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ua.goit.client.Client;
import ua.goit.planet.Planet;
import ua.goit.ticket.Ticket;

import java.util.List;


public class HibernateUtil {
    private static final HibernateUtil INSTANCE;

    private SessionFactory sessionFactory;

    static {
        INSTANCE = new HibernateUtil();
    }
    private HibernateUtil() {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Planet.class)
                .addAnnotatedClass(Ticket.class)
                .buildSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static HibernateUtil getInstance() {
        return INSTANCE;
    }
    public void close() {
        sessionFactory.close();
    }

}
