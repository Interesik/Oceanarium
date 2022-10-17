package org.nbd.entities;

import org.nbd.utils.TicketType;

import java.util.Date;

public abstract class SingleTicket extends Ticket {

    private final TicketType ticketType;
    private final Client client;

    public SingleTicket(Date visitDate, Float basePrice, TicketType ticketType, Client client) {
        super(basePrice, visitDate);
        this.ticketType = ticketType;
        this.client = client;
    }

    public abstract double applyDiscount(double basePrice);

    public double getActualPrice() {
        return applyDiscount(getBasePrice());
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        return "SingleTicket{" +
                "ticketType=" + ticketType +
                ", client=" + client +
                ", visitDate=" + getVisitDate() +
                ", ActualPrice=" + getActualPrice() +
                ", ticketID=" + getTicketID() +
                '}';
    }
}
