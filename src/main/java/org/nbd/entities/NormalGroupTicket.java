package org.nbd.entities;

import java.util.Date;
import java.util.List;

public class NormalGroupTicket extends GroupTicket{
    public NormalGroupTicket(Date visitDate, float basePrice, List<Client> clients) {
        super(visitDate,basePrice, clients);
    }

    @Override
    public double applyDiscount(double basePrice) {
        return basePrice;
    }
}
