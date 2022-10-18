package org.nbd.entities;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Client extends AbstractEntity{

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

    public Client(String firstName, String lastName, Date birthdayDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdayDate = birthdayDate;
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
}
