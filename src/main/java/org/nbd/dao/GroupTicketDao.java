package org.nbd.dao;

import org.nbd.entities.Client;
import org.nbd.entities.GroupTicket;
import org.nbd.entities.NormalGroupTicket;
import org.nbd.entities.ReductedGroupTicket;
import org.nbd.utils.ClientType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.List;

public class GroupTicketDao implements Dao<GroupTicket> {
    private EntityTransaction transaction;
    private final EntityManager em;

    public GroupTicketDao(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(GroupTicket obj) {
        transaction = em.getTransaction();
        transaction.begin();
        em.persist(obj);
        transaction.commit();
    }

    @Override
    public GroupTicket read(long id) {
        return em.find(GroupTicket.class,id);
    }

    @Override
    public void update(Runnable updateFunction) {
        transaction = em.getTransaction();
        transaction.begin();
        updateFunction.run();
        transaction.commit();
    }

    @Override
    public void delete(GroupTicket obj) {
        transaction.begin();
        em.remove(obj);
        transaction.commit();
    }
    public void createNewGroupTicket(Float basePrice, Date visitDate, ClientType clientType,List<Client> clients) {
        GroupTicket newTicket;
        switch (clientType) {
            case NORMAL:
                newTicket = new NormalGroupTicket(visitDate,basePrice,clients);
                create(newTicket);
                break;
            case STUDENT:
            case REDUCTED:
            case SENIOR:
                newTicket = new ReductedGroupTicket(visitDate,basePrice,clients);
                create(newTicket);
                break;
        }
    }
    public void updateBasePrice(GroupTicket ticket, Float basePrice) {
        update(() -> ticket.setBasePrice(basePrice));
    }

    public void updateVisitDate(GroupTicket ticket, Date date) {
        update(() -> ticket.setVisitDate(date));
    }

    public void updateAddClient(GroupTicket ticket,Client client){update(()->ticket.addClient(client));}

    public void updateRemoveClient(GroupTicket ticket,Client client){update(()->ticket.getClients().remove(client));}
}
