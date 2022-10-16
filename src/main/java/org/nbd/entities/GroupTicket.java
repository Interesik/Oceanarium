package org.nbd.entities;

import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

public abstract class GroupTicket extends Ticket {

    @ManyToOne
    List<Client> Clients;

    public GroupTicket(Date visitDate, Float basePrice, List<Client> clients) {
        super(basePrice,visitDate);
        this.Clients = clients;
    }

    @Override
    public double getActualPrice() {
        return Clients.size() * applyDiscount(getBasePrice());
    }
    public abstract double applyDiscount(double basePrice);

    @Override //TODO: zrobić poprawnie toString
    public String toString() {
        return "GroupTicket{" +
                "Clients=" + Clients +
                '}';
    }
}
