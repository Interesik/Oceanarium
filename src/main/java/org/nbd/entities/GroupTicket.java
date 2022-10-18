package org.nbd.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
public abstract class GroupTicket extends Ticket {
    @NotNull
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable
    private List<Client> clients;

    public GroupTicket(Float basePrice, Date visitDate, List<Client> clients) {
        super(basePrice, visitDate);
        this.clients = clients;
    }
    protected GroupTicket(){

    }

    public double getActualPrice() {
        return clients.size() * applyDiscount(getBasePrice());
    }

    public abstract double applyDiscount(double basePrice);

    @Override
    public String toString() {
        return "GroupTicekt{" +
                "clients=" + clients +
                ", visitDate=" + getVisitDate() +
                ", ActualPrice=" + getActualPrice() +
                ", ticketID=" + getTicketID() +
                '}';
    }
}
