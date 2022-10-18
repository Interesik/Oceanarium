package org.nbd.dao;

import org.nbd.entities.Client;
import org.nbd.entities.Ticket;
import org.nbd.utils.TicketTypeEnum.TicketDiscount;
import org.nbd.utils.TicketTypeEnum.TicketSubtype;
import org.nbd.utils.TicketTypeEnum.TicketType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

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
        transaction = em.getTransaction();
        transaction.begin();
        em.remove(ticket);
        transaction.commit();
    }

    public void createNewTicket(double basePrice, Date visitDate, List<Client> clients, TicketType ticketType, TicketSubtype ticketSubtype, TicketDiscount ticketDiscount) {
        Ticket newTicket = new Ticket(basePrice, visitDate, clients, ticketType, ticketSubtype, ticketDiscount);
        create(newTicket);
    }
}
