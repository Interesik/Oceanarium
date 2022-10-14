package org.example.model;

import java.util.Date;

public abstract class Ticket {
    Date visitDate;
    int basePrice;
    int ticketID;
    boolean archive = false;

    public Ticket(Date visitDate, Integer basePrice, int ticketID) {
        this.visitDate = visitDate;
        this.basePrice = basePrice;
        this.ticketID = ticketID;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public int getTicketID() {
        return ticketID;
    }
    public abstract double getActualPrice();

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "visitDate=" + visitDate +
                ", basePrice=" + basePrice +
                ", ticketID=" + ticketID +
                ", archive=" + archive +
                '}';
    }
}
