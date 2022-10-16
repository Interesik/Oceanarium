package org.nbd.entities;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
public abstract class SingleTicket extends Ticket {

    public enum TicketType{normal, cityCard, charity}
    private TicketType ticketType;
    @OneToOne
    private Client client;

    public SingleTicket(Date visitDate, Float basePrice, TicketType ticketType, org.nbd.entities.Client client) {
        super(basePrice,visitDate);
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
                ", visitDate=" + getVisitDate() +
                ", ActualPrice=" + getActualPrice() +
                ", archive=" + isArchive() +
                '}';
    }
}
