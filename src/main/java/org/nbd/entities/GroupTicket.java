package org.nbd.entities;

import org.nbd.utils.TicketTypeEnum.GroupTicketType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@DiscriminatorValue("group")
public class GroupTicket extends Ticket implements Discount {

    @ManyToMany
    @JoinTable(name = "clients",
            joinColumns = @JoinColumn(name = "ticket_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    private List<Client> clientsList;

    @Column(name = "group_ticket_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private GroupTicketType ticketType;

    @Version
    @Column(name = "version")
    private long version;

    private final double basePrice = getBasePrice();

    public GroupTicket() {
    }

    public GroupTicket(double basePrice, Date visitDate, List<Client> clientsList, GroupTicketType ticketType) {
        super(basePrice, visitDate);
        this.clientsList = clientsList;
        this.ticketType = ticketType;
    }

    public List<Client> getClientsList() {
        return clientsList;
    }

    public void setClientsList(List<Client> clientsList) {
        this.clientsList = clientsList;
    }

    public GroupTicketType getTicketType() {
        return ticketType;
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

        if (ticketType == GroupTicketType.REDUCED) {
            return basePrice * 0.5;
        } else {
            return basePrice;
        }
    }

    @Override
    public double getActualPrice() {
        return clientsList.size() * applyDiscount();
    }

    @Override
    public String toString() {

        StringBuilder clientsNamesList = new StringBuilder();
        for (int i = 0; i < clientsList.size(); i++) {
            clientsNamesList.append(clientsList.get(i).getFirstName()).append(" ").append(clientsList.get(i).getLastName());
            if (i != clientsList.size() - 1) clientsNamesList.append(", ");
        }

        return "GroupTicket {" +
                " clients = " + clientsNamesList +
                ", visit date = " + getVisitDate() +
                ", actual price = " + getActualPrice() +
                ", ticketID = " + ticketType.toString() +
                '}';
    }
}
