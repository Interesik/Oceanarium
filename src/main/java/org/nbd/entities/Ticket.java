package org.nbd.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
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
