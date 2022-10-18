package org.nbd.entities;

import org.nbd.utils.ClientType;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@Entity
@Table(name = "client_table")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long personalID;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birthday_date", nullable = false)
    private Date birthdayDate;

    @Column(name = "client_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClientType clientType;

    @JoinColumn(name = "ticket_id", referencedColumnName = "id")
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "client_tickets",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "ticket_id", referencedColumnName = "id"))
    private List<Ticket> tickets;

    @Version
    private long version;

    public Client() {
    }

    public Client(String firstName, String lastName, Date birthdayDate, ClientType clientType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
        this.clientType = clientType;
    }

    public long getPersonalID() {
        return personalID;
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

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public int getAge() {
        Calendar dateOfBirth = new GregorianCalendar();
        Calendar currentDate = new GregorianCalendar();
        dateOfBirth.setTime(birthdayDate);
        int age = currentDate.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        if ((dateOfBirth.get(Calendar.MONTH) > currentDate.get(Calendar.MONTH)) ||
                ((dateOfBirth.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH) &&
                        dateOfBirth.get(Calendar.DAY_OF_MONTH) > currentDate.get(Calendar.DAY_OF_MONTH)))) {
            age--;
        }

        return age;
    }

    public long getVersion() {
        return version;
    }
}
