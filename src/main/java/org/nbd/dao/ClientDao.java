package org.nbd.dao;

import org.nbd.entities.Client;
import org.nbd.entities.GroupTicket;
import org.nbd.entities.SingleTicket;
import org.nbd.utils.ClientType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class ClientDao implements Dao<Client> {

    private EntityTransaction transaction;
    private final EntityManager em;

    public ClientDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public long create(Client client) {
        transaction = em.getTransaction();
        transaction.begin();
        em.persist(client);
        transaction.commit();

        return client.getPersonalID();
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
        if (client.getSingleTickets() != null) {
            for (SingleTicket singleTicket : client.getSingleTickets()) em.remove(singleTicket);
        }
        if (client.getGroupTickets() != null) {
            for (GroupTicket groupTicket : client.getGroupTickets()) {
                if (groupTicket.getClientsList().size() == 1) em.remove(groupTicket);
                else if (groupTicket.getClientsList().size() > 1) {
                    List<Client> newClientsList = groupTicket.getClientsList();
                    newClientsList.remove(client);
                    groupTicket.setClientsList(newClientsList);
                }
            }
        }
        em.remove(client);
        transaction.commit();
    }

    public long createNewClient(String firstName, String lastName, Date birthdayDate, ClientType clientType) {
        Client newClient = new Client(firstName, lastName, birthdayDate, clientType);
        return create(newClient);
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
}
