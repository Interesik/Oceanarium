package org.nbd.entities;


import java.util.Date;

public class StudentTicket extends SingleTicket{
    public StudentTicket(Date visitDate, float basePrice, TicketType ticketType, Client client) {
        super(visitDate, basePrice, ticketType, client);
    }

    @Override
    public double applyDiscount(double basePrice) {
        basePrice*=0.5;
        switch(getTicketType()){
            case normal:
                return basePrice;
            case cityCard:
                return basePrice*0.8;
            case charity:
                return basePrice+1;
        }
        return basePrice;
    }
}
