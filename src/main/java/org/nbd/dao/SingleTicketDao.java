package org.nbd.dao;

import org.nbd.entities.Client;
import org.nbd.entities.SingleTicket;
import org.nbd.utils.TicketTypeEnum.TicketDiscount;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SingleTicketDao implements Dao<SingleTicket> {
    private EntityTransaction transaction;
    private final EntityManager em;

    public SingleTicketDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public long create(SingleTicket singleTicket) {
        transaction = em.getTransaction();
        transaction.begin();
        em.persist(singleTicket);
        transaction.commit();
        return singleTicket.getTicketID();
    }

    @Override
    public SingleTicket read(long id) {
        return em.find(SingleTicket.class, id);
    }

    @Override
    public void update(Runnable updateFunction) {
        transaction = em.getTransaction();
        transaction.begin();
        updateFunction.run();
        transaction.commit();
    }

    @Override
    public void delete(SingleTicket singleTicket) {
        transaction.begin();
        em.remove(singleTicket);
        transaction.commit();
    }

    public long createNewSingleTicket(double basePrice, Date visitDate, Client client, TicketDiscount discount) {
        SingleTicket newTicket = new SingleTicket(basePrice, visitDate, client, discount);
        List<SingleTicket> newListOfSingleTickets;

        if (client.getSingleTickets() == null)
            newListOfSingleTickets = new ArrayList<>();
        else
            newListOfSingleTickets = client.getSingleTickets();

        newListOfSingleTickets.add(newTicket);
        client.setSingleTickets(newListOfSingleTickets);

        return create(newTicket);
    }
}
