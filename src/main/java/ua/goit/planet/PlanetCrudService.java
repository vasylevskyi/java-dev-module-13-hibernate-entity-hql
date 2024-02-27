package ua.goit.planet;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.client.Client;
import ua.goit.client.ClientCrudService;
import ua.goit.database.HibernateUtil;
import ua.goit.exceptions.IdOutOfRangeException;
import ua.goit.ticket.Ticket;

public class PlanetCrudService {


    //Get planet by Id
    public Planet getPlanetById(String id) throws IdOutOfRangeException {
        Planet existingPlanet;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            existingPlanet = session.get(Planet.class, id);
        }
        PlanetCrudService.validatePlanet(id, existingPlanet);
        return existingPlanet;
    }

    //Create new planet
    public String createPlanet (String id, String name) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Planet newPlanet = new Planet();
                newPlanet.setId(id);
                newPlanet.setName(name);
                session.persist(newPlanet);
                String newPlanetId = newPlanet.getId();
            transaction.commit();
        session.close();
        return newPlanetId;
    }

    public int deletePlanetById(String id) throws IdOutOfRangeException {
        Planet existingPlanet;
        int isDeleted;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
                existingPlanet = session.get(Planet.class, id);
                PlanetCrudService.validatePlanet(id, existingPlanet);
                existingPlanet.setDeleted();
                session.persist(existingPlanet);
                isDeleted = existingPlanet.getDeleted();
            transaction.commit();
        }
        return isDeleted;
    }

    public static void validatePlanet(String id, Planet existingPlanet) throws IdOutOfRangeException {
        if (existingPlanet == null) {
            throw new IdOutOfRangeException("Planet with id = " + id + " doesn't exists");
        }
    }
}

