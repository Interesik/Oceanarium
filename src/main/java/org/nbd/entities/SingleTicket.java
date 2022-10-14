package org.nbd.entities;

import org.example.model.Client;
import org.example.model.Ticket;

import java.util.Date;

public abstract class SingleTicket extends Ticket {

    enum TicketType{normal, cityCard, charity};
    private TicketType ticketType;
    org.nbd.entities.Client client;

    public SingleTicket(Date visitDate, Integer basePrice, int ticketID, TicketType ticketType, org.example.model.Client client) {
        super(visitDate, basePrice, ticketID);
        this.ticketType = ticketType;
        this.client = client;
    }

    public abstract double applyDiscount(double basePrice);

    @Override
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
                ", visitDate=" + visitDate +
                ", ActualPrice=" + getActualPrice() +
                ", ticketID=" + ticketID +
                ", archive=" + archive +
                '}';
    }
}
