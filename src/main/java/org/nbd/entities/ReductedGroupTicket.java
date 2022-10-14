package org.nbd.entities;

import org.example.model.Client;

import java.util.Date;
import java.util.List;

public class ReductedGroupTicket extends GroupTicket{
    public ReductedGroupTicket(Date visitDate, Integer basePrice, int ticketID, List<Client> clients) {
        super(visitDate, basePrice, ticketID, clients);
    }

    @Override
    public double applyDiscount(double basePrice) {
        return basePrice*0.5;
    }
}

