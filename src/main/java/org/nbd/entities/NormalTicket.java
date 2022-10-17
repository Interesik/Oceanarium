package org.nbd.entities;

import org.nbd.utils.TicketType;

import java.util.Date;

public class NormalTicket extends SingleTicket {
    public NormalTicket(Date visitDate, Float basePrice, TicketType ticketType, Client client) {
        super(visitDate, basePrice, ticketType, client);
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
