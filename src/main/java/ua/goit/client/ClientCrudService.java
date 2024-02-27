package ua.goit.client;

import net.n2oapp.criteria.api.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.collection.spi.PersistentList;
import ua.goit.database.HibernateUtil;
import ua.goit.exceptions.IdOutOfRangeException;
import ua.goit.ticket.Ticket;

import java.util.List;

public class ClientCrudService {


    //Get client by Id

    public Client getClientById(long id) throws IdOutOfRangeException {
        Client existingClient;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            String query = ("SELECT c FROM Client c LEFT JOIN FETCH c.tickets WHERE c.id = " + id);
            existingClient = session
                    .createQuery(query, Client.class)
                    .getSingleResult();
            ClientCrudService.validateClient(id, existingClient);
        }
        return existingClient;
    }

    //Create new client
    public long createClient (String name) {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
                Client newClient = new Client();
                newClient.setName(name);
                session.persist(newClient);
                long id = newClient.getId();
            transaction.commit();
        session.close();
        return id;
    }

    //Delete client by id
    public int deleteClientById(long id) throws IdOutOfRangeException {
        Client existingClient;
        int isDeleted;
        try (Session session = HibernateUtil.getInstance().getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
                existingClient = session.get(Client.class, id);
                ClientCrudService.validateClient(id, existingClient);
                existingClient.setDeleted();
                session.persist(existingClient);
            isDeleted = existingClient.getDeleted();
            transaction.commit();
        }
        return isDeleted;
    }

    //Get all clients
    public List<Client> getAllClients () {
        Session session = HibernateUtil.getInstance().getSessionFactory().openSession();
        List<Client> clientsList = session.createQuery("SELECT c FROM Client c LEFT JOIN FETCH c.tickets"
                , Client.class)
                .list();
        return clientsList;
    }


    public static void validateClient(long id, Client existingClient) throws IdOutOfRangeException {
        if (existingClient == null) {
            throw new IdOutOfRangeException("Client with id = " + id + " doesn't exists");
        }
    }
}

//List all clients
/*        List<Client> clientsList = session.createQuery("from Client", Client.class).list();
        System.out.println("clients = " + clients);*/
