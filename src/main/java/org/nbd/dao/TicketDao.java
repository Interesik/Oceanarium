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
        return em.find(Ticket.class, id);
    }

    @Override
    public void create(Ticket ticket) {
        transaction = em.getTransaction();
        transaction.begin();
        em.persist(ticket);
        transaction.commit();
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
        transaction.begin();
        em.remove(ticket);
        transaction.commit();
    }

    public void createNewTicket(Float basePrice, Date visitDate) {
        Ticket newTicket = new Ticket(basePrice, visitDate);
        create(newTicket);
    }

    public void updateBasePrice(Ticket ticket, Float basePrice) {
        update(() -> ticket.setBasePrice(basePrice));
    }

    public void updateVisitDate(Ticket ticket, Date date) {
        update(() -> ticket.setVisitDate(date));
    }
}
