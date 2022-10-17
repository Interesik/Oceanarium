package org.nbd.entities;

import java.util.Date;
import java.util.List;

public class ReductedGroupTicket extends GroupTicket{
    public ReductedGroupTicket(Date visitDate, Float basePrice, List<Client> clients) {
        super(basePrice, visitDate, clients);
    }

    @Override
    public double applyDiscount(double basePrice) {
        return basePrice*0.5;
    }
}

