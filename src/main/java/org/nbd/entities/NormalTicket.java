package org.nbd.entities;

import org.nbd.utils.TicketType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
@Entity
@DiscriminatorValue("Normal")
public class NormalTicket extends SingleTicket {
    public NormalTicket(Date visitDate, Float basePrice, TicketType ticketType, Client client) {
        super(visitDate, basePrice, ticketType, client);
    }

    protected NormalTicket() {

    }

    @Override
    public double applyDiscount(double basePrice) {
        return switch (getTicketType()) {
            case NORMAL -> basePrice;
            case CITY_CARD -> basePrice * 0.6;
            case CHARITY -> basePrice + 1;
        };
    }
}
