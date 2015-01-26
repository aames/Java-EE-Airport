package net.ajed.airport.dto;

import java.io.Serializable;
/**
 *
 * @author Andrew
 */
public class CustomerDTO implements Serializable {
    private Integer id;
    private String firstname;
    private String lastname;
    private Integer passportnumber;
    private Integer luggage;
    private Integer flight;
    private Integer address;

    public CustomerDTO(Integer id, String firstname, String lastname, Integer passportnumber, Integer luggage, Integer flight, Integer address) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.passportnumber = passportnumber;
        this.luggage = luggage;
        this.flight = flight;
        this.address = address;
    }
    public CustomerDTO(Integer id, String firstname, String lastname, Integer passportnumber) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.passportnumber = passportnumber;
        
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getPassportnumber() {
        return passportnumber;
    }

    public void setPassportnumber(Integer passportnumber) {
        this.passportnumber = passportnumber;
    }

    public Integer getLuggage() {
        return luggage;
    }

    public void setLuggage(Integer luggage) {
        this.luggage = luggage;
    }

    public Integer getFlight() {
        return flight;
    }

    public void setFlight(Integer flight) {
        this.flight = flight;
    }

    public Integer getAddress() {
        return address;
    }

    public void setAddress(Integer Address) {
        this.address = Address;
    }
}
