package org.example.repositories;

import org.example.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TicketRepository {
    List<Ticket> tickets;
    public void add(Ticket newTicket){
        tickets.add(newTicket);
    }
    public void remove(Ticket oldTicket){
        for (Ticket e:tickets) {
            if (oldTicket.equals(e)) {
                e.setArchive(true);
                return;
            }
        }
    }
    public List<Ticket> find(Predicate<Ticket> predicate){
        List<Ticket> results = new ArrayList();
        for(int i=0; i<size(); i++){
            if(predicate.test(tickets.get(i)) == true){
                results.add(tickets.get(i));
            }
        }
        return results;
    }
    public boolean testTrue(Ticket ticket){
        return true;
    }
    public String raport(){
        return tickets.toString();
    }

    public Ticket getTicket(int i) {
        return tickets.get(i);
    }

    public int size(){
        return tickets.size();
    }
}
