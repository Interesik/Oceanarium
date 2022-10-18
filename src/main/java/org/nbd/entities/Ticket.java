package org.nbd.entities;

import com.sun.istack.NotNull;
import org.nbd.utils.TicketTypeEnum.TicketDiscount;
import org.nbd.utils.TicketTypeEnum.TicketSubtype;
import org.nbd.utils.TicketTypeEnum.TicketType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket_table")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long ticketID;

    @Column(name = "base_price", nullable = false)
    private double basePrice;

    @Column(name = "visit_date", nullable = false)
    private Date visitDate;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tickets")
    @NotNull
    private List<Client> clients;

    @Column(name = "ticket_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketType ticketType;

    @Column(name = "ticket_subtype", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketSubtype ticketSubtype;

    @Column(name = "ticket_discount", nullable = false)
    @Enumerated(EnumType.STRING)
    private TicketDiscount ticketDiscount;

    protected Ticket() {
    }

    public Ticket(double basePrice, Date visitDate, List<Client> clients, TicketType ticketType, TicketSubtype ticketSubtype, TicketDiscount ticketDiscount) {
        this.basePrice = basePrice;
        this.visitDate = visitDate;
        this.clients = clients;
        this.ticketType = ticketType;
        this.ticketSubtype = ticketSubtype;
        this.ticketDiscount = ticketDiscount;
    }

    public long getTicketID() {
        return ticketID;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public double getActualPrice() {
        if (ticketType == TicketType.SINGLE) {
            SingleTicket singleTicket = new SingleTicket();
            return singleTicket.applyDiscount();
        } else {
            GroupTicket groupTicket = new GroupTicket();
            return groupTicket.applyDiscount();
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public TicketSubtype getTicketSubtype() {
        return ticketSubtype;
    }

    public TicketDiscount getTicketDiscount() {
        return ticketDiscount;
    }
}
