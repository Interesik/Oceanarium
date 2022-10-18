package org.nbd.entities;

import org.nbd.utils.ClientType;

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

    @Override
    public void addClient(Client newClient) {
        if(!(newClient.getClientType() == ClientType.NORMAL)){
            getClients().add(newClient);
        }else{
            //throw logic
        }
    }
}
