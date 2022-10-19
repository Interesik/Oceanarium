package org.nbd.dao;

import org.nbd.entities.Client;
import org.nbd.entities.GroupTicket;
import org.nbd.utils.TicketTypeEnum.GroupTicketType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GroupTicketDao implements Dao<GroupTicket> {
    private EntityTransaction transaction;
    private final EntityManager em;

    public GroupTicketDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public long create(GroupTicket groupTicket) {
        transaction = em.getTransaction();
        transaction.begin();
        em.persist(groupTicket);
        transaction.commit();

        return groupTicket.getTicketID();
    }

    @Override
    public GroupTicket read(long id) {
        return em.find(GroupTicket.class, id);
    }

    @Override
    public void update(Runnable updateFunction) {
        transaction = em.getTransaction();
        transaction.begin();
        updateFunction.run();
        transaction.commit();
    }

    @Override
    public void delete(GroupTicket groupTicket) {
        transaction.begin();
        em.remove(groupTicket);
        transaction.commit();
    }

    public long createNewGroupTicket(double basePrice, Date visitDate, List<Client> clients, GroupTicketType ticketType) {
        GroupTicket newTicket = new GroupTicket(basePrice, visitDate, clients, ticketType);
        List<GroupTicket> newListOfGroupTickets;

        for (Client client : clients) {
            if (client.getGroupTickets() == null)
                newListOfGroupTickets = new ArrayList<>();
            else
                newListOfGroupTickets = client.getGroupTickets();
            newListOfGroupTickets.add(newTicket);
            client.setGroupTickets(newListOfGroupTickets);
        }

        return create(newTicket);
    }
}
