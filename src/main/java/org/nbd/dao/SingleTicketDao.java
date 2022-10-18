package org.nbd.dao;

import org.nbd.entities.*;
import org.nbd.utils.TicketType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;

public class SingleTicketDao implements Dao<SingleTicket>{
    private EntityTransaction transaction;
    private final EntityManager em;

    public SingleTicketDao(EntityManager em) {
        this.em = em;

    }

    @Override
    public void create(SingleTicket ticket) {
        transaction = em.getTransaction();
        transaction.begin();
        em.persist(ticket);
        transaction.commit();
    }

    @Override
    public SingleTicket read(long id) {
        return em.find(SingleTicket.class,id);
    }

    @Override
    public void update(Runnable updateFunction) {
        transaction = em.getTransaction();
        transaction.begin();
        updateFunction.run();
        transaction.commit();
    }

    @Override
    public void delete(SingleTicket obj) {
        transaction.begin();
        em.remove(obj);
        transaction.commit();
    }
    public void createNewSingleTicket(Float basePrice, Date visitDate, TicketType ticketType, Client client) {
        SingleTicket newTicket;
        switch (client.getClientType()) {
            case NORMAL:
                newTicket = new NormalTicket(visitDate,basePrice,ticketType,client);
                create(newTicket);
                break;
            case STUDENT:
            case REDUCTED:
                newTicket = new StudentTicket(visitDate,basePrice,ticketType,client);
                create(newTicket);
                break;
            case SENIOR:
                newTicket = new SeniorTicket(visitDate,basePrice,ticketType,client);
                create(newTicket);
                break;
        }
    }
    public void updateBasePrice(SingleTicket ticket, Float basePrice) {
        update(() -> ticket.setBasePrice(basePrice));
    }

    public void updateVisitDate(SingleTicket ticket, Date date) {
        update(() -> ticket.setVisitDate(date));
    }
}
