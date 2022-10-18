package org.nbd;

import org.nbd.dao.ClientDao;
import org.nbd.dao.TicketDao;
import org.nbd.entities.Client;
import org.nbd.utils.ClientType;
import org.nbd.utils.TicketTypeEnum;
import org.nbd.utils.TicketTypeEnum.TicketDiscount;
import org.nbd.utils.TicketTypeEnum.TicketSubtype;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class App {

    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static final EntityManager em = factory.createEntityManager();

    public static void main(String[] args) {
        ClientDao clientDao = new ClientDao(em);
        TicketDao ticketDao = new TicketDao(em);

        clientDao.createNewClient("Olek", "Kobusinski", new Date(1992, Calendar.JUNE, 16), ClientType.STUDENT);

        Client client = clientDao.read(1);

        Date testDate = new Date("10/20/2020");
        clientDao.updateFirstName(client, "Aleksander");

        ticketDao.createNewTicket(10.0f, testDate, List.of(client), TicketTypeEnum.TicketType.SINGLE, TicketSubtype.NORMAL, TicketDiscount.NORMAL);
        clientDao.delete(client);
    }
}
