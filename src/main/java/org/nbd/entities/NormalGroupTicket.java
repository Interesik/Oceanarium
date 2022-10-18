package org.nbd.entities;

import java.util.Date;
import java.util.List;

public class NormalGroupTicket extends GroupTicket{
    public NormalGroupTicket(Date visitDate, Float basePrice, List<Client> clients) {
        super(basePrice, visitDate, clients);
    }

    @Override
    public double applyDiscount(double basePrice) {
        return basePrice;
    }

    @Override
    public void addClient(Client newClient) {
        getClients().add(newClient);
    }
}
