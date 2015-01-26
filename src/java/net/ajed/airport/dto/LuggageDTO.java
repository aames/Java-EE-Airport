package net.ajed.airport.dto;

import java.io.Serializable;

/**
 *
 * @author Andrew
 */
public class LuggageDTO implements Serializable {
    
    private Integer id;
    private Integer flight;
    private Integer customer;
    private Double mass;

    
    public LuggageDTO(Integer id, Double mass){
        this.id = id;
        this.mass = mass;
    }
    public LuggageDTO(Integer id, Integer flight, Integer customer, Double mass) {
        this.id = id;
        this.flight = flight;
        this.customer = customer;
        this.mass = mass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlight() {
        return flight;
    }

    public void setFlight(Integer flight) {
        this.flight = flight;
    }

    public Integer getCustomer() {
        return customer;
    }

    public void setCustomer(Integer customer) {
        this.customer = customer;
    }

    public Double getMass() {
        return mass;
    }

    public void setMass(Double mass) {
        this.mass = mass;
    }
}
