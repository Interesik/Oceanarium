package org.nbd.crud;

import org.nbd.entities.Client;

import javax.persistence.*;
import java.util.Date;

public class ClientRepository {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static EntityManager em  = factory.createEntityManager();

    private EntityTransaction transaction;

    public void createNewClient (String firstName, String lastName, Date birthdayDate) {

        Client newClient = new Client (firstName, lastName, birthdayDate);
        transaction = em.getTransaction();

        transaction.begin();

        em.persist(newClient);
        System.out.println(" New Guest ID " + newClient.getPersonalID());

        transaction.commit();
    }

    public Client findById(long i) {
        em.clear();
        return em.find(Client.class, i);
    }

    public void updateFirstName(Client client, String firstName) {
        transaction = em.getTransaction();
        transaction.begin();
        client.setFirstName(firstName);
        transaction.commit();
    }

    public void updateLastName(Client client, String lastName) {
        transaction = em.getTransaction();
        transaction.begin();
        client.setLastName(lastName);
        transaction.commit();
    }

    public void updateBirthdayDate(Client client, Date date) {
        transaction = em.getTransaction();
        transaction.begin();
        client.setBirthdayDate(date);
        transaction.commit();
    }

    public void delete(Client client) {
        transaction.begin();
        em.remove(client);
        transaction.commit();
    }
}
