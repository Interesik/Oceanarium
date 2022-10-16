package org.nbd.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID ticketID; // TODO: Wykorzystać klase UUID zamias long
    private Float basePrice;
    private Date visitDate;
    private boolean archive;

    public Ticket(Float basePrice, Date visitDate) {
        this.basePrice = basePrice;
        this.visitDate = visitDate;
        this.archive = false;
    }
    //Potrzebne przy wyczytywaniu z bazy danych wartości
    protected Ticket() {

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

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public UUID getTicketID() {
        return ticketID;
    }

    public abstract double getActualPrice();
}
