package org.nbd.dao;

import org.nbd.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class TicketDao implements Dao<Ticket> {

    private EntityTransaction transaction;
    private final EntityManager em;

    public TicketDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public Ticket read(long id) {
        return em.getReference(Ticket.class, id);
    }

    @Override
    public long create(Ticket ticket) {
        transaction = em.getTransaction();
        transaction.begin();
        em.persist(ticket);
        transaction.commit();

        return ticket.getTicketID();
    }

    @Override
    public void update(Runnable updateFunction) {
        transaction = em.getTransaction();
        transaction.begin();
        updateFunction.run();
        transaction.commit();
    }

    @Override
    public void delete(Ticket ticket) {
        transaction = em.getTransaction();
        transaction.begin();
        em.remove(ticket);
        transaction.commit();
    }

    public long createNewTicket(double basePrice, Date visitDate) {
        Ticket newTicket = new Ticket(basePrice, visitDate);
        return create(newTicket);
    }
}
