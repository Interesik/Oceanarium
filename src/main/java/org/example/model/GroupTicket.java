package org.example.model;

import java.util.Date;
import java.util.List;

public abstract class GroupTicket extends Ticket{
    List<Client> Clients;

    public GroupTicket(Date visitDate, Integer basePrice, int ticketID, List<Client> clients) {
        super(visitDate, basePrice, ticketID);
        Clients = clients;
    }

    @Override
    public double getActualPrice() {
        return Clients.size() * applyDiscount(getBasePrice());
    }
    public abstract double applyDiscount(double basePrice);

    @Override
    public String toString() {
        return "GroupTicekt{" +
                "Clients=" + Clients +
                ", visitDate=" + visitDate +
                ", ActualPrice=" + getActualPrice() +
                ", ticketID=" + ticketID +
                ", archive=" + archive +
                '}';
    }
}
