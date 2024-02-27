package ua.goit;

import org.hibernate.Session;
import ua.goit.client.Client;
import ua.goit.client.ClientCrudService;
import ua.goit.database.Database;
import ua.goit.database.DatabaseInitService;
import ua.goit.database.HibernateUtil;
import ua.goit.exceptions.IdOutOfRangeException;
import ua.goit.planet.Planet;
import ua.goit.planet.PlanetCrudService;
import ua.goit.ticket.Ticket;
import ua.goit.ticket.TicketCrudService;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IdOutOfRangeException {

        //Migration

        Database database = Database.getInstance();

        new DatabaseInitService().initDb(database);

        database.close();

        System.out.println("Migration");


        //Module 13

        //Ticket CRUD

        //Get Ticket by id
        Ticket existingTicket = new TicketCrudService().getTicketById(3L);
        System.out.println("existingTicket = " + existingTicket);

        //Create new Ticket
        long newTicketId = new TicketCrudService().createTicket(8L, "MARS4", "VEN2");
        System.out.println("newTicketId = " + newTicketId);

        //Get all Tickets by client_id
        List<Ticket> allClientTickets = new TicketCrudService().getAllClientTickets(3L);
        System.out.println("allClientTickets = " + allClientTickets);

        //Delete Ticket

        int isDeleted = new TicketCrudService().deleteTicketById(9L);
        System.out.println("isDeleted = " + isDeleted);



        //Module 12

        //ClIENT CRUD

        //Get client by id
/*        Client existingClient = new ClientCrudService().getClientById(3L);
        System.out.println("existingClient = " + existingClient);*/

        //Create new client
//        long clientId = new ClientCrudService().createClient("Bruce Willis");
//        System.out.println("newClient id = " + clientId);


        //Delete client
//        System.out.println("is deleted = " + new ClientCrudService().deleteClientById(3L));

        //Get all clients
//        List<Client> clientsList = new ClientCrudService().getAllClients();
//        System.out.println("clientsList = " + clientsList);


        //PLANET CRUD

        //Get Panet by Id
/*        Planet existingPlanet = new PlanetCrudService().getPlanetById("PLUTO9");
        System.out.println("existingPlanet = " + existingPlanet);*/

        //Create new planet
/*        String planetId = new PlanetCrudService().createPlanet("REA20", "Reach");
        System.out.println("newPlanet Id = " + planetId);*/

        //Delete Planet
/*        System.out.println("is deleted = " + new PlanetCrudService().deletePlanetById("JUP5"));*/





    }
}