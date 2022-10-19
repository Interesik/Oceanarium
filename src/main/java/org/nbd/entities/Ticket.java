package org.nbd.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ticket_table")
@Inheritance(strategy = InheritanceType.JOINED)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private long ticketID;

    @Column(name = "base_price", nullable = false)
    private double basePrice;

    @Column(name = "visit_date", nullable = false)
    private Date visitDate;

    @Version
    @Column(name = "version")
    private long version;

    protected Ticket() {
    }

    public Ticket(double basePrice, Date visitDate) {
        this.basePrice = basePrice;
        this.visitDate = visitDate;
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

    public long getVersion() {
        return version;
    }
}
