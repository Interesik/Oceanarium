package org.nbd;

import org.nbd.dao.ClientDao;
import org.nbd.dao.GroupTicketDao;
import org.nbd.dao.SingleTicketDao;
import org.nbd.entities.Client;
import org.nbd.entities.SingleTicket;
import org.nbd.utils.ClientType;
import org.nbd.utils.TicketTypeEnum;
import org.nbd.utils.TicketTypeEnum.TicketDiscount;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class App {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static final EntityManager em = factory.createEntityManager();

    public static void main(String[] args) {
        ClientDao clientDao = new ClientDao(em);
        SingleTicketDao singleTicketDao = new SingleTicketDao(em);
        GroupTicketDao groupTicketDao = new GroupTicketDao(em);

        long studentID = clientDao.createNewClient("Olek", "Kobusinski", new Date(1992, Calendar.JUNE, 16), ClientType.STUDENT);
        long seniorID = clientDao.createNewClient("Karol", "Kazusek", new Date("4/18/2020"), ClientType.NORMAL);
        long normalClientID = clientDao.createNewClient("Jan", "Kowalski", new Date("4/18/1960"), ClientType.SENIOR);
        Client client = clientDao.read(studentID);
        Client client2 = clientDao.read(seniorID);
        Client client3 = clientDao.read(normalClientID);
        System.out.println(client.getPersonalID());

        Date testDate = new Date("10/20/2020");
        clientDao.updateFirstName(client, "Aleksander");

        long normalSingleTicketID = singleTicketDao.createNewSingleTicket(10.0, testDate, client, TicketDiscount.NORMAL);
        long cityCardSingleTicketID = singleTicketDao.createNewSingleTicket(10.0, testDate, client2, TicketDiscount.CITY_CARD);
        long charitySingleTicketID = singleTicketDao.createNewSingleTicket(10.0, testDate, client3, TicketDiscount.CHARITY);

        SingleTicket normalSingleTicket = singleTicketDao.read(normalSingleTicketID);

        List<Client> clients = new ArrayList<>();
        clients.add(client);
        clients.add(client2);
        clients.add(client3);
        groupTicketDao.createNewGroupTicket(10.0, testDate, clients, TicketTypeEnum.GroupTicketType.REDUCED);

        clientDao.delete(client);
        if (singleTicketDao.read(normalSingleTicketID) != null)
            singleTicketDao.delete(normalSingleTicket);
    }
}
