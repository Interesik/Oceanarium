package org.example.model;

import java.util.Date;

public class SeniorTicket extends SingleTicket{
    public SeniorTicket(Date visitDate, Integer basePrice, int ticketID, TicketType ticketType, Client client) {
        super(visitDate, basePrice, ticketID, ticketType, client);
    }

    @Override
    public double applyDiscount(double basePrice) {
        basePrice *= 0.8;
        switch (getTicketType()) {
            case normal:
                return basePrice;
            case cityCard:
                return basePrice * 0.6;
            case charity:
                return basePrice + 1;
        }
        return basePrice;
    }
}
