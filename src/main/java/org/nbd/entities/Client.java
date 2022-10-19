package org.nbd.entities;

import com.sun.istack.NotNull;
import org.nbd.dao.TicketDao;
import org.nbd.utils.ClientType;
import org.nbd.utils.LogicException;
import org.nbd.utils.TicketType;
import org.nbd.dao.ClientDao;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Client extends AbstractEntity{

    private ClientType clientType;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long personalID;
    public String firstName;
    @NotNull
    public String lastName;
    @NotNull
    public Date birthdayDate;
    public Client() {
    }

    public Client(String firstName, String lastName, Date birthdayDate,ClientType clientType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
        this.clientType = clientType;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
    public void createNewSingleTicket(Float basePrice, Date visitDate, TicketType ticketType,TicketDao TD,ClientDao CD) {
        try {
            int amountActive = 0;
            for(int i = 0;i < this.getTickets().size();i++)
                if(!(this.getTickets().get(i).isArchive())){
                    amountActive += 1;
                }
            if (amountActive >= 3) {
                throw new LogicException();
            }
        }catch (LogicException ex){
            System.out.println("Klient ma za dużo bieltów");
            return;
        }
        SingleTicket newTicket;
        switch (this.getClientType()) {
            case NORMAL:
                newTicket = new NormalTicket(visitDate, basePrice, ticketType, this);
                CD.updateTicketList(this, newTicket);
                TD.create(newTicket);
                break;
            case STUDENT:
            case REDUCTED:
                newTicket = new StudentTicket(visitDate, basePrice, ticketType, this);
                CD.updateTicketList(this, newTicket);
                TD.create(newTicket);
                break;
            case SENIOR:
                newTicket = new SeniorTicket(visitDate, basePrice, ticketType, this);
                CD.updateTicketList(this, newTicket);
                TD.create(newTicket);
                break;
        }
    }
    public void createNewGroupTicket(Float basePrice, Date visitDate, List<Client> clients, TicketDao TD,ClientDao CD) {
        try {
            for (Client c : clients) {
                int amountActive = 0;
                for(int i = 0;i < c.getTickets().size();i++)
                    if(!(c.getTickets().get(i).isArchive())){
                        amountActive += 1;
                    }
                    if (amountActive >= 3) {
                        throw new LogicException();
                    }
            }
        }catch (LogicException ex){
            System.out.println("Jedne z klietnów ma za dużo bieltów");
            return;
        }
        GroupTicket newTicket;
        switch (this.getClientType()) {
            case NORMAL:
                newTicket = new NormalGroupTicket(visitDate, basePrice, clients);
                AddTicket(clients,TD,CD,newTicket);
                break;
            case STUDENT:
            case REDUCTED:
            case SENIOR:
                newTicket = new ReductedGroupTicket(visitDate, basePrice, clients);
                AddTicket(clients,TD,CD,newTicket);
                break;
        }
    }
    public void updateBasePrice(Ticket ticket, Float basePrice, TicketDao TD) {
        TD.update(() -> ticket.setBasePrice(basePrice));
    }

    public void updateVisitDate(Ticket ticket, Date date,TicketDao TD) {
        TD.update(() -> ticket.setVisitDate(date));
    }

    public void updateAddClient(GroupTicket ticket,Client client,TicketDao TD,ClientDao CD) {
        if(!(client.getTickets().size() >= 3)) {
            CD.updateTicketList(client,ticket);
            TD.update(()->ticket.addClient(client));
        }

    }
    public void updateRemoveClient(GroupTicket ticket,Client client, TicketDao TD,ClientDao CD)
    {
        TD.update(()->ticket.getClients().remove(client));
        CD.update(()->client.getTickets().remove(ticket));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdayDate() {
        return birthdayDate;
    }

    public void setBirthdayDate(Date birthdayDate) {
        this.birthdayDate = birthdayDate;
    }

    public long getPersonalID() {
        return personalID;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
    private void AddTicket(List<Client> clients,TicketDao TD,ClientDao CD,GroupTicket newTicket){
        for(Client c: clients) {
            CD.updateTicketList(c,newTicket);
        }
        TD.create(newTicket);
    }
}
