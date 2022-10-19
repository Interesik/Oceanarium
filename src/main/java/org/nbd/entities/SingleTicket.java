package org.nbd.entities;

import org.nbd.utils.ClientType;
import org.nbd.utils.TicketDiscountConverter;
import org.nbd.utils.TicketTypeEnum.TicketDiscount;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("single")
public class SingleTicket extends Ticket implements Discount {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_client")
    private Client client;

    @Column(name = "ticket_discount", nullable = false)
    @Convert(converter = TicketDiscountConverter.class)
    private TicketDiscount ticketDiscount;

    @Version
    @Column(name = "version")
    private long version;

    double basePrice = getBasePrice();
    ClientType clientType;

    public SingleTicket() {
    }

    public SingleTicket(double basePrice, Date visitDate, Client client, TicketDiscount ticketDiscount) {
        super(basePrice, visitDate);
        this.client = client;
        this.clientType = client.getClientType();
        this.ticketDiscount = ticketDiscount;
    }

    public Client getClient() {
        return client;
    }

    public TicketDiscount getTicketDiscount() {
        return ticketDiscount;
    }

    @Override
    public double getBasePrice() {
        return basePrice;
    }

    public long getVersion() {
        return version;
    }

    @Override
    public double applyDiscount() {

        if (clientType == ClientType.STUDENT) {
            basePrice *= 0.5;
        } else if (clientType == ClientType.SENIOR) {
            basePrice *= 0.8;
        }

        switch (ticketDiscount) {
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
    public double getActualPrice() {
        return applyDiscount();
    }

    @Override
    public String toString() {
        return "SingleTicket {" +
                "ticket type = single" +
                ", client = " + client.getFirstName() + " " + client.getLastName() + " " + client.getClientType() +
                ", visit date = " + getVisitDate() +
                ", actual price = " + getActualPrice() +
                ", discount = " + ticketDiscount +
                ", ticketID = " + getTicketID() +
                '}';
    }
}
