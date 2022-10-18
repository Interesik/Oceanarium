package org.nbd.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;
import java.util.List;
@Entity
@DiscriminatorValue("Normal")
public class NormalGroupTicket extends GroupTicket{
    public NormalGroupTicket(Date visitDate, Float basePrice, List<Client> clients) {
        super(basePrice, visitDate, clients);
    }

    protected NormalGroupTicket() {

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
