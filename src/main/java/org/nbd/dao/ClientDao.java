package org.nbd.dao;

import org.nbd.entities.Client;
import org.nbd.entities.Ticket;
import org.nbd.utils.ClientType;

import javax.persistence.*;
import java.util.Date;

public class ClientDao implements Dao<Client> {

    private EntityTransaction transaction;
    private final EntityManager em;

    public ClientDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Client client) {
        transaction = em.getTransaction();
        transaction.begin();
        em.persist(client);
        em.lock(client, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        transaction.commit();
    }

    @Override
    public Client read(long id) {
        return em.find(Client.class, id);
    }

    @Override
    public void update(Runnable updateFunction) {
        transaction = em.getTransaction();
        transaction.begin();
        updateFunction.run();
        transaction.commit();
    }

    @Override
    public void delete(Client client) {
        transaction = em.getTransaction();
        transaction.begin();
        client.removeTicket();
        em.remove(client);
        transaction.commit();
    }

    public void createNewClient(String firstName, String lastName, Date birthdayDate, ClientType clientType) {
        Client newClient = new Client(firstName, lastName, birthdayDate, clientType);
        create(newClient);
    }

    public void updateFirstName(Client client, String firstName) {
        update(() -> client.setFirstName(firstName));
    }

    public void updateLastName(Client client, String lastName) {
        update(() -> client.setLastName(lastName));
    }

    public void updateBirthdayDate(Client client, Date date) {
        update(() -> client.setBirthdayDate(date));
    }
    public void updateTicketList(Client client, Ticket ticket) { update(() -> client.getTickets().add(ticket));}
}
