package org.nbd.entities;

import org.nbd.utils.TicketType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
public class SeniorTicket extends SingleTicket {
    public SeniorTicket(Date visitDate, Float basePrice, TicketType ticketType, Client client) {
        super(visitDate, basePrice, ticketType, client);
    }

    protected SeniorTicket() {

    }

    @Override
    public double applyDiscount(double basePrice) {
        return basePrice = basePrice * 0.8 + getTicketType().getValue();

    }
}
