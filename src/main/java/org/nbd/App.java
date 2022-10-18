package org.nbd;

import org.nbd.dao.ClientDao;
import org.nbd.dao.TicketDao;
import org.nbd.entities.Client;
import org.nbd.utils.ClientType;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.Date;

public class App {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static final EntityManager em  = factory.createEntityManager();

    public static void main(String[] args) {
        ClientDao clientDao = new ClientDao(em);
        TicketDao ticketDao = new TicketDao(em);
        clientDao.createNewClient("Olek", "Kobusinski", new Date(1992, Calendar.JUNE, 16), ClientType.STUDENT);
        Client client = clientDao.read(1);
        System.out.println(client.getPersonalID());
        Date d1 = new Date("10/20/2020");
        clientDao.updateFirstName(client, "Aleksander");
        ticketDao.createNewTicket(10.0f,d1);
        clientDao.delete(client);
    }
}
