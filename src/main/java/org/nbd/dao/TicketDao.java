package org.nbd.dao;

import org.nbd.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.LockModeType;
import javax.transaction.Transactional;
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
        em.lock(ticket, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
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
        em.lock(ticket, LockModeType.OPTIMISTIC_FORCE_INCREMENT);
        em.remove(ticket);
        transaction.commit();
    }
}
