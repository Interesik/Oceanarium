package org.nbd.entities;

import org.nbd.utils.TicketTypeEnum.TicketSubtype;

import java.util.List;

public class GroupTicket extends Ticket implements Discount {

    final double basePrice = getBasePrice();
    final List<Client> clientList = getClients();
    final TicketSubtype ticketSubtype = getTicketSubtype();

    public double getActualPrice() {
        return clientList.size() * applyDiscount();
    }

    @Override
    public double applyDiscount() {

        if (ticketSubtype == TicketSubtype.REDUCED) {
            return basePrice * 0.5;
        } else {
            return basePrice;
        }
    }

    @Override
    public String toString() {

        String clientsNamesList = "";
        for (int i = 0; i < clientList.size(); i++) {
            clientsNamesList += clientList.get(i).getFirstName() + " " + clientList.get(i).getLastName();
            if (i != clientList.size() - 1) clientsNamesList += ", ";
        }

        return "GroupTicket {" +
                " clients = " + clientsNamesList +
                ", visit date = " + getVisitDate() +
                ", actual price = " + getActualPrice() +
                ", discount = " + getTicketDiscount() +
                ", ticketID = " + getTicketID() +
                '}';
    }
}
