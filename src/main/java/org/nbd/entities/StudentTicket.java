package org.nbd.entities;

import org.nbd.utils.TicketType;

import javax.persistence.*;
import java.util.Date;
@Entity
@Access(AccessType.FIELD)
@DiscriminatorValue("Student")
public class StudentTicket extends SingleTicket {
    public StudentTicket(Date visitDate, Float basePrice, TicketType ticketType, Client client) {
        super(visitDate, basePrice, ticketType, client);
    }

    protected StudentTicket() {

    }

    @Override
    public double applyDiscount(double basePrice) {
        basePrice *= 0.5;
        return switch (getTicketType()) {
            case NORMAL -> basePrice;
            case CITY_CARD -> basePrice * 0.8;
            case CHARITY -> basePrice + 1;
        };
    }
}
