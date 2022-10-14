package org.example.model;

import java.util.Date;

public abstract class SingleTicket extends Ticket {

    enum TicketType{normal, cityCard, charity};
    private TicketType ticketType;
    Client client;

    public SingleTicket(Date visitDate, Integer basePrice, int ticketID, TicketType ticketType, Client client) {
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
