package org.nbd.entities;

import org.nbd.utils.TicketType;

import javax.persistence.*;
import java.util.Date;
@Entity
@Access(AccessType.FIELD)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Type")
public abstract class SingleTicket extends Ticket {
    private TicketType ticketType;
    @OneToOne
    @JoinColumn(name = "Client_ID")
    private Client client;

    public SingleTicket(Date visitDate, Float basePrice,TicketType ticketType, Client client) {
        super(basePrice, visitDate);
        this.ticketType = ticketType;
        this.client = client;
    }
    protected SingleTicket(){

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
