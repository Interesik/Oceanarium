package org.nbd.crud;

import org.nbd.entities.SeniorTicket;
import org.nbd.entities.SingleTicket;
import org.nbd.entities.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

public class TicketRepository {

    private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    // krzyczy że oba Entity mogą być final
    private static EntityManager em  = factory.createEntityManager();

    private EntityTransaction transaction;

    // czy wykonujemy dla każdego rodzaju biletu odzielną transakcje
    public void createNewSeniorTicket (Float basePrice, Date visitDate, SingleTicket.TicketType ticketType, org.nbd.entities.Client client) {
        Ticket newTicket = new SeniorTicket(visitDate,basePrice,ticketType,client);
        transaction = em.getTransaction();

        transaction.begin();

        em.persist(newTicket);
        System.out.println(" New Guest ID " + newTicket.getTicketID());

        transaction.commit();
    }

    public Ticket findById(long i) {
        em.clear();
        return em.find(Ticket.class, i);
    }

    public void updateBasePrice(Ticket ticket, Float basePrice) {
        transaction = em.getTransaction();
        transaction.begin();
        ticket.setBasePrice(basePrice);
        transaction.commit();
    }

    public void updateVisitDate(Ticket ticket, Date date) {
        transaction = em.getTransaction();
        transaction.begin();
        ticket.setVisitDate(date);
        transaction.commit();
    }

    public void delete(Ticket ticket) {
        transaction.begin();
        em.remove(ticket);
        transaction.commit();
    }
}
