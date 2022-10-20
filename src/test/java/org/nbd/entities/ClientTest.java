package org.nbd.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nbd.dao.ClientDao;
import org.nbd.dao.TicketDao;
import org.nbd.utils.ClientType;
import org.nbd.utils.TicketType;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("thePersistenceUnit");
    private static final EntityManager em  = factory.createEntityManager();
    private static final EntityManager em2  = factory.createEntityManager();
    TicketDao TD = new TicketDao(em);
    TicketDao TD2 = new TicketDao(em);
    ClientDao CD = new ClientDao(em);
    ClientDao CD2 = new ClientDao(em);
    @BeforeEach
    void setUp() {
        CD.createNewClient("Olek", "Kobusinski", new Date(1992, Calendar.JUNE, 16), ClientType.STUDENT);
        CD.createNewClient("Karol", "Kazusek", new Date("4/18/2020"), ClientType.NORMAL);
        CD.createNewClient("Jan", "Kowalski", new Date("4/18/1960"), ClientType.SENIOR);
    }
    @Test
    void createNewSingleTicket() {
        Client client = CD.read(1);
        Client client2 = CD.read(2);
        Client client3 = CD.read(3);
        client.createNewSingleTicket(10.0f, new Date(1 / 1 / 1111), TicketType.NORMAL, TD, CD);
        client.createNewSingleTicket(10.0f, new Date(1 / 1 / 1111), TicketType.NORMAL, TD, CD);
        assertEquals(client.getTickets().size(), 2);
        //Wyścig
        new Thread(() -> client.createNewSingleTicket(10.0f, new Date(2 / 2 / 2222), TicketType.CHARITY, TD2, CD2)).start();
        new Thread(() -> client.createNewSingleTicket(10.0f, new Date(1 / 1 / 1111), TicketType.CHARITY, TD2, CD2)).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertEquals(client.getTickets().size(), 3);
        //Sprawdzenie czy liczy isArchive = true
        client.getTickets().get(0).setArchive(true);
        client.createNewSingleTicket(10.0f, new Date(1 / 1 / 1111), TicketType.NORMAL, TD, CD);
        assertEquals(client.getTickets().size(), 4);
    }
    @Test
    void createNewGroupTicket() {
        Client client = CD.read(1);
        Client client2 = CD.read(2);
        Client client3 = CD.read(3);
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        clients.add(client2);
        clients.add(client3);
        client.createNewGroupTicket(10.0f,new Date("10/1/2000"),clients,TD,CD);
        client.createNewGroupTicket(10.0f,new Date("10/1/2000"),clients,TD,CD);
        // Wyscig
        assertEquals(client.getTickets().size(),2);
        new Thread(() -> client.createNewGroupTicket(10.0f,new Date("10/1/2000"),clients,TD,CD)).start();
        new Thread(() -> client.createNewGroupTicket(10.0f,new Date("11/3/2021"),clients,TD,CD)).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertEquals(client3.getTickets().size(),3);
        //Sprawdzenie czy liczy isArchive = true
        client.getTickets().get(0).setArchive(true);
        client.createNewGroupTicket(10.0f, new Date(1 / 1 / 1111),clients, TD, CD);
        assertEquals(client.getTickets().size(), 4);
    }



    @Test
    void LockTest(){
        EntityTransaction transaction1 = em.getTransaction();
        EntityTransaction transaction2 = em2.getTransaction();
        new Thread(() ->{
            transaction1.begin();
            em.find(Ticket.class, 2l,LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            assertThrows(RollbackException.class,() ->transaction1.commit());
        }).start();
        new Thread(() ->{
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            transaction2.begin();
            em2.find(Ticket.class,1l,LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            transaction2.commit();
        }).start();
        new Thread(() ->{
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            transaction2.begin();
            em2.find(Ticket.class,1l,LockModeType.OPTIMISTIC_FORCE_INCREMENT);
            transaction2.commit();
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Test
    void Update() {
        Client client = CD.read(1);
        Client client2 = CD.read(2);
        client.createNewSingleTicket(10.0f, new Date(1 / 1 / 1111), TicketType.NORMAL, TD, CD);
        //updateBasePrice
        assertEquals(10.0f,client.getTickets().get(0).getBasePrice());
        client.updateBasePrice(TD.read(1), 20.0f,TD);
        assertEquals(20.0f,client.getTickets().get(0).getBasePrice());
        //updateVisitDate
        assertEquals(client.getTickets().get(0).getVisitDate(),new Date(1 / 1 / 1111));
        client.updateVisitDate(TD.read(1), new Date(1 / 1 / 2222),TD);
        assertEquals(client.getTickets().get(0).getVisitDate(),new Date(1 / 1 / 2222));
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        clients.add(client2);
        //Test updateRemoveClient
        assertEquals(0,client2.getTickets().size());
        client.createNewGroupTicket(10.0f,new Date(1/1/3333),clients,TD,CD);
        assertEquals(1,client2.getTickets().size());
        client.updateRemoveClient((GroupTicket) TD.read(2),client2,TD,CD);
        assertEquals(0,client2.getTickets().size());
    }

    @Test
    void Delete() {
        Client client = CD.read(1);
        Client client2 = CD.read(2);
        assertEquals(0,client.getTickets().size());
        client.createNewSingleTicket(10.0f, new Date(1 / 1 / 1111), TicketType.NORMAL, TD, CD);
        assertEquals(1,client.getTickets().size());
        //DeleteSingleTicekt
        TD.delete(TD.read(1));
        assertEquals(0,client.getTickets().size());
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        clients.add(client2);
        assertEquals(0,client2.getTickets().size());
        assertEquals(0,client.getTickets().size());
        client.createNewGroupTicket(10.0f,new Date(1/1/3333),clients,TD,CD);
        assertEquals(1,client2.getTickets().size());
        assertEquals(1,client.getTickets().size());
        TD.delete(TD.read(2l));
        assertEquals(0,client2.getTickets().size());
        assertEquals(0,client.getTickets().size());
        client.createNewGroupTicket(10.0f,new Date(1/1/3333),clients,TD,CD);
        CD.delete(CD.read(2l));

    }
}