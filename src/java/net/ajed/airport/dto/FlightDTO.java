package net.ajed.airport.dto;

/**
 *
 * @author Andrew
 */
public class FlightDTO {
    
    private Integer id;
    private String destination;
    private Integer gate;

    public FlightDTO(Integer id, String destination, Integer gate) {
        this.id = id;
        this.destination = destination;
        this.gate = gate;
    }
public FlightDTO(Integer id, String destination) {
        this.id = id;
        this.destination = destination;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getGate() {
        return gate;
    }

    public void setGate(Integer gate) {
        this.gate = gate;
    }
}
