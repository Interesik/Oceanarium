package org.nbd.entities;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ticketID;
    private Float basePrice;
    private Date visitDate;

    protected Ticket() {
    }

    public Ticket(Float basePrice, Date visitDate) {
        this.basePrice = basePrice;
        this.visitDate = visitDate;
    }

    public Float getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Float basePrice) {
        this.basePrice = basePrice;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public long getTicketID() {
        return ticketID;
    }
}
