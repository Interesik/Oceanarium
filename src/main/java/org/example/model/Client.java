package org.example.model;

import java.util.Date;

public class Client {
    String firstName;
    String lastName;
    String personalID;
    Date birthday;

    public boolean isArchive() {
        return archive;
    }

    public void setArchive(boolean archive) {
        this.archive = archive;
    }

    boolean archive = false;
    public Client(String firstName, String lastName, String personalID, Date birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalID = personalID;
        this.birthday = birthday;
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

    public String getPersonalID() {
        return personalID;
    }

    public int getAge() {
        return  birthday.getYear();
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Client{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalID='" + personalID + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}

