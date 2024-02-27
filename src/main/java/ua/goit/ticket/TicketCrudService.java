package ua.goit.ticket;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ua.goit.client.Client;
import ua.goit.client.ClientCrudService;
import ua.goit.database.HibernateUtil;
import ua.goit.exceptions.IdOutOfRangeException;
import ua.goit.planet.Planet;
import ua.goit.planet.PlanetCrudService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class TicketCrudService {

    //Get ticket by Id
    public Ticket getTicketById(long id) throws IdOutOfRangeException {
        Ticket existingTicket;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            existingTicket = session.get(Ticket.class, id);
        }
        TicketCrudService.validateTicket(id, existingTicket);


/*        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            String query = ("SELECT t FROM Ticket t LEFT JOIN FETCH t.client WHERE t.id = " + id);
            existingTicket = session
                    .createQuery(query, Ticket.class)
                    .getSingleResult();
            TicketCrudService.validateTicket(id, existingTicket);
        }*/

        return existingTicket;
    }

    //Create new ticket
    public long createTicket (long clientId, String fromPlanetId, String toPlanetId) throws IdOutOfRangeException {
        Client client;
        Planet fromPlanet;
        Planet toPlanet;
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                client = session.get(Client.class, clientId);
                ClientCrudService.validateClient(clientId, client);
                fromPlanet = session.get(Planet.class, fromPlanetId);
                PlanetCrudService.validatePlanet(fromPlanetId, fromPlanet);
                toPlanet = session.get(Planet.class, toPlanetId);
                PlanetCrudService.validatePlanet(toPlanetId,toPlanet);
                Ticket newTicket = new Ticket();
                newTicket.setCreatedAt(ZonedDateTime.now(
                        ZoneId.of( "UTC" )
                ));
                newTicket.setClient(client);
                newTicket.setFromPlanet(fromPlanet);
                newTicket.setToPlanet(toPlanet);
                session.persist(newTicket);
                long newTicketId = newTicket.getId();
            transaction.commit();
        session.close();
        return newTicketId;
    }

    //Get all Tickets by client_id
    public List<Ticket> getAllClientTickets (long clientId) {
        String query = "SELECT * from Ticket WHERE client_id = " + clientId;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            List<Ticket> allClientTickets = session.createNativeQuery(query, Ticket.class).list();
            return allClientTickets;
        }
   }

    // Delete ticket by Id
    public int deleteTicketById(long id) throws IdOutOfRangeException {
        Ticket existingTicket;
        int isDeleted;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
                existingTicket = session.get(Ticket.class, id);
                TicketCrudService.validateTicket(id, existingTicket);
                existingTicket.setDeleted();
                session.persist(existingTicket);
                isDeleted = existingTicket.getDeleted();
            transaction.commit();
        }
        return isDeleted;
    }

    //

    public static void validateTicket(long id, Ticket existingTicket) throws IdOutOfRangeException {
        if (existingTicket == null) {
            throw new IdOutOfRangeException("Ticket with id = " + id + " doesn't exists");
        }
    }
}
