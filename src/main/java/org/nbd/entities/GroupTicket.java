package org.nbd.entities;

import java.util.Date;
import java.util.List;

public abstract class GroupTicket extends Ticket {
    private final List<Client> clients;

    public GroupTicket(Float basePrice, Date visitDate, List<Client> clients) {
        super(basePrice, visitDate);
        this.clients = clients;
    }

    public double getActualPrice() {
        return clients.size() * applyDiscount(getBasePrice());
    }

    public abstract double applyDiscount(double basePrice);

    @Override
    public String toString() {
        return "GroupTicekt{" +
                "clients=" + clients +
                ", visitDate=" + getVisitDate() +
                ", ActualPrice=" + getActualPrice() +
                ", ticketID=" + getTicketID() +
                '}';
    }
}
