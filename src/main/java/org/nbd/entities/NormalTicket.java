package org.nbd.entities;
import java.util.Date;

public class NormalTicket extends SingleTicket{
    public NormalTicket(Date visitDate, float basePrice, TicketType ticketType, Client client) {
        super(visitDate, basePrice, ticketType, client);
    }

    @Override
    public double applyDiscount(double basePrice) {
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
