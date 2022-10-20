package org.nbd;

import org.nbd.dao.ClientDao;
import org.nbd.dao.TicketDao;
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
        TicketDao TD = new TicketDao(em);
        clientDao.createNewClient("Olek", "Kobusinski", new Date(1992, Calendar.JUNE, 16), ClientType.STUDENT);
        clientDao.createNewClient("Karol", "Kazusek", new Date("4/18/2020"), ClientType.NORMAL);
        clientDao.createNewClient("Jan", "Kowalski", new Date("4/18/1960"), ClientType.SENIOR);
        Client client = clientDao.read(1);
        Client client2 = clientDao.read(2);
        Client client3 = clientDao.read(3);
        Date d1 = new Date("10/20/2020");
        Date d2 = new Date("2/1/2022");
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        clients.add(client2);
        clients.add(client3);
        client.createNewSingleTicket(10.0f,d1,TicketType.CHARITY,TD,clientDao);
        client.createNewSingleTicket(10.0f,d2,TicketType.NORMAL,TD,clientDao);
        client.createNewSingleTicket(10.0f,d1,TicketType.CITY_CARD,TD,clientDao);
        client.createNewSingleTicket(10.0f,d1,TicketType.CITY_CARD,TD,clientDao);
        client2.createNewGroupTicket(10.0f,d1,clients,TD,clientDao);

    }
}
