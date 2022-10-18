package org.nbd.entities;

import org.nbd.utils.ClientType;

public class SingleTicket extends Ticket implements Discount {

    double basePrice = getBasePrice();
    final Client client = getClients().get(0);
    final ClientType clientType = client.getClientType();

    @Override
    public double applyDiscount() {

        if (clientType == ClientType.STUDENT) {
            basePrice *= 0.5;
        } else if (clientType == ClientType.SENIOR) {
            basePrice *= 0.8;
        }

        switch (getTicketDiscount()) {
            case CITY_CARD -> {
                switch (clientType) {
                    case STUDENT -> {
                        return basePrice * 0.8;
                    }
                    case SENIOR -> {
                        return basePrice * 0.6;
                    }
                    default -> {
                        return basePrice;
                    }
                }

            }
            case CHARITY -> {
                return basePrice + 1;
            }
            default -> {
                return basePrice;
            }
        }
    }

    @Override
    public String toString() {
        return "SingleTicket {" +
                "ticket type = single" +
                ", client = " + client.getFirstName() + " " + client.getLastName() + " " + client.getClientType() +
                ", visit date = " + getVisitDate() +
                ", actual price = " + getActualPrice() +
                ", discount = " + getTicketDiscount() +
                ", ticketID = " + getTicketID() +
                '}';
    }
}
