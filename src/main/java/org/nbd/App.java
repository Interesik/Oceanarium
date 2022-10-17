package org.nbd;

import org.nbd.dao.ClientDao;
import org.nbd.entities.Client;

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
        clientDao.createNewClient("Olek", "Kobusinski", new Date(1992, Calendar.JUNE, 16));
        Client client = clientDao.read(1);
        System.out.println(client.getPersonalID());
        clientDao.updateFirstName(client, "Aleksander");
        clientDao.delete(client);
    }
}
