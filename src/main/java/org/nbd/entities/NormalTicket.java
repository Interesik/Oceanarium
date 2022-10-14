package org.nbd.entities;

import org.example.model.Client;

import java.util.Date;

public class NormalTicket extends SingleTicket{
    public NormalTicket(Date visitDate, Integer basePrice, int ticketID, TicketType ticketType, Client client) {
        super(visitDate, basePrice, ticketID, ticketType, client);
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
