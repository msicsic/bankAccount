package com.sg.kata.bankaccount.business;

import com.sg.kata.bankaccount.business.dddframework.BusinessValue;

import java.time.LocalDate;
import java.util.Objects;

@BusinessValue
public class Client {
    private String firstName;
    private String lastName;
    private LocalDate dob;

    public Client(String firstName, String lastName, LocalDate dob) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) &&
                Objects.equals(lastName, client.lastName) &&
                Objects.equals(dob, client.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dob);
    }
}
