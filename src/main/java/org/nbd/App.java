package org.nbd;

import org.nbd.dao.ClientDao;
import org.nbd.dao.GroupTicketDao;
import org.nbd.dao.SingleTicketDao;
import org.nbd.entities.Client;
import org.nbd.utils.ClientType;
import org.nbd.utils.TicketType;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class App {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static final EntityManager em  = factory.createEntityManager();

    public static void main(String[] args) {
        ClientDao clientDao = new ClientDao(em);
        //TicketDao ticketDao = new TicketDao(em);
        SingleTicketDao singleTicketDao = new SingleTicketDao(em);
        GroupTicketDao groupTicketDao = new GroupTicketDao(em);
        clientDao.createNewClient("Olek", "Kobusinski", new Date(1992, Calendar.JUNE, 16), ClientType.STUDENT);
        clientDao.createNewClient("Karol", "Kazusek", new Date("4/18/2020"), ClientType.NORMAL);
        clientDao.createNewClient("Jan", "Kowalski", new Date("4/18/1960"), ClientType.SENIOR);
        Client client = clientDao.read(1);
        Client client2 = clientDao.read(2);
        Client client3 = clientDao.read(3);
        System.out.println(client.getPersonalID());
        Date d1 = new Date("10/20/2020");
        clientDao.updateFirstName(client, "Aleksander");
        singleTicketDao.createNewSingleTicket(10.0f,d1, TicketType.NORMAL,client);
        singleTicketDao.createNewSingleTicket(10.0f,d1, TicketType.NORMAL,client2);
        singleTicketDao.createNewSingleTicket(10.0f,d1, TicketType.NORMAL,client3);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        clients.add(client2);
        clients.add(client3);
        groupTicketDao.createNewGroupTicket(10.0f,d1,ClientType.NORMAL,clients);

        //ticketDao.createNewTicket(10.0f,d1);
    }
}
